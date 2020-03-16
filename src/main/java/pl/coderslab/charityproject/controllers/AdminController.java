package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charityproject.models.CurrentUser;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.services.UserService;
import pl.coderslab.charityproject.validationGroups.EditedUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @RequestMapping
    public String mainPage() {
        return "admin-main/admin-main";
    }

    @RequestMapping("/admins")
    public String adminsList() {
        return "admin-admins/admins-list";
    }

    @RequestMapping("/delete-admin/{instId}")
    public String deleteAdminAction(Model model, @AuthenticationPrincipal CurrentUser currentUser,
                                    @PathVariable("instId") Long instId) {
        if (currentUser.getUser().getId().equals(instId)) {
            model.addAttribute("stopDelete", "stopDelete");
            return "admin-admins/admins-list";
        }

        User admin = userService.findById(instId);
        userService.deleteUser(admin);

        return "redirect:admin/admins";
    }

    @RequestMapping("/edit-admin/{adminId}")
    public String editAdmin(Model model, @PathVariable("adminId") Long instId) {
        User admin = userService.findById(instId);
        model.addAttribute("admin", admin);
        return "admin-admins/edit-admin";
    }

    @PostMapping("/edit-admin-action")
    public String editAdminAction(@ModelAttribute("admin") @Validated({EditedUser.class}) User admin,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-admins/edit-admin";
        }

        admin.setPassword(userService.findById(admin.getId()).getPassword());

        userService.saveUser(admin, "admin", false);

        return "redirect:admin/admins";
    }

    @RequestMapping("/add-admin")
    public String addAdmin(Model model) {
        model.addAttribute("admin", new User());
        return "admin-admins/add-admin";
    }

    @PostMapping("/add-admin-action")
    public String addAdminAction(Model model, @RequestParam("password2") String pass2,
                                 @ModelAttribute("admin") @Valid User admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-admins/add-admin";
        }

        if (!admin.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "admin-admins/add-admin";
        }

        userService.saveUser(admin, "admin", true);

        model.addAttribute("passNoMatch", false);

        return "redirect:admin/admins";
    }

    @ModelAttribute("admins")
    public List<User> admins() {
        return userService.findAdmins();
    }
}

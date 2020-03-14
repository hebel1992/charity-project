package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.services.UserService;

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
        return "/admins/admins-list";
    }

    @RequestMapping("/delete-admin/{instId}")
    public String deleteAdminAction(@PathVariable("instId") Long instId) {
        User admin = userService.findById(instId);
        userService.deleteUser(admin);

        return "redirect:/admin/admins";
    }

    @RequestMapping("/edit-admin/{adminId}")
    public String editAdmin(Model model, @PathVariable("adminId") Long instId) {
        User admin = userService.findById(instId);
        model.addAttribute("admin", admin);
        return "/admins/edit-admin";
    }

    @PostMapping("/edit-admin-action")
    public String editAdminAction(Model model, @RequestParam("password2") String pass2,
                                        @ModelAttribute("admin") @Valid User admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admins/edit-admin";
        }

        if (!admin.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "/admins/add-admin";
        }

        userService.saveAdmin(admin);

        return "redirect:/admin/admins";
    }

    @RequestMapping("/add-admin")
    public String addAdmin(Model model) {
        model.addAttribute("admin", new User());
        return "/admins/add-admin";
    }

    @PostMapping("/add-admin-action")
    public String addAdminAction(Model model, @RequestParam("password2") String pass2,
                                 @ModelAttribute("admin") @Valid User admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admins/add-admin";
        }

        if (!admin.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "/admins/add-admin";
        }

        userService.saveAdmin(admin);

        model.addAttribute("passNoMatch", false);

        return "redirect:/admin/admins";
    }

    @ModelAttribute("admins")
    public List<User> admins() {
        return userService.findAdmins();
    }
}

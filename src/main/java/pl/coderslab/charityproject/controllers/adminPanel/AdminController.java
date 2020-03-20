package pl.coderslab.charityproject.controllers.adminPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charityproject.models.Category;
import pl.coderslab.charityproject.models.CurrentUser;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;
import pl.coderslab.charityproject.validationGroups.EditedUser;
import pl.coderslab.charityproject.validationGroups.UserChangingPassword;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

    private final CategoryService categoryService;

    @Autowired
    public AdminController(DonationService donationService, CategoryService categoryService,
                           UserService userService, InstitutionService institutionService) {
        super(donationService, userService, institutionService);
        this.categoryService = categoryService;
    }

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

        return "redirect:/admin/admins";
    }

    @RequestMapping("/edit-admin/{adminId}")
    public String editAdmin(Model model, @PathVariable("adminId") Long instId,
                            @RequestParam(value = "passwordChanged", required = false) String passwordChanged) {
        User admin = userService.findById(instId);
        model.addAttribute("admin", admin);
        model.addAttribute("passwordChanged", passwordChanged);
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

        return "redirect:/admin/admins";
    }

    @RequestMapping("/change-admin-password/{adminId}")
    public String changeAdminPassword(Model model, @PathVariable("adminId") Long adminId) {
        User user = userService.findById(adminId);
        model.addAttribute("admin", user);
        return "admin-admins/change-admin-password";
    }

    @PostMapping("/change-admin-password-action")
    public String changeAdminPasswordAction(Model model, @RequestParam("password2") String pass2,
                                           @ModelAttribute("admin") @Validated({UserChangingPassword.class}) User admin,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-admins/change-admin-password";
        }

        if (!admin.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "admin-admins/change-admin-password";
        }

        userService.saveUser(admin, "admin", true);

        return "redirect:/admin/edit-admin/" + admin.getId() + "?passwordChanged=yes";
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

        return "redirect:/admin/admins";
    }

    @ModelAttribute("admins")
    public List<User> admins() {
        return userService.findAdmins();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }
}


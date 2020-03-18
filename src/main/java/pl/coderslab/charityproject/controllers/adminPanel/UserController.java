package pl.coderslab.charityproject.controllers.adminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;
import pl.coderslab.charityproject.validationGroups.EditedUser;
import pl.coderslab.charityproject.validationGroups.UserChangePassword;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserController extends AbstractController {

    public UserController(DonationService donationService, UserService userService,
                          InstitutionService institutionService) {
        super(donationService, userService, institutionService);
    }

    @RequestMapping("/users")
    public String userList() {
        return "admin-users/users-list";
    }

    @RequestMapping("/delete-user/{instId}")
    public String deleteUserAction(@PathVariable("instId") Long instId) {
        User user = userService.findById(instId);
        userService.deleteUser(user);

        return "redirect:/admin/users";
    }

    @RequestMapping("/edit-user/{userId}")
    public String editUser(Model model, @PathVariable("userId") Long instId,
                           @RequestParam(value = "passwordChanged", required = false) String passwordChanged) {
        User user = userService.findById(instId);
        model.addAttribute("user", user);
        model.addAttribute("passwordChanged", passwordChanged);
        return "admin-users/edit-user";
    }

    @PostMapping("/edit-user-action")
    public String editUserAction(@ModelAttribute("user") @Validated({EditedUser.class}) User user,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-users/edit-user";
        }

        user.setPassword(userService.findById(user.getId()).getPassword());

        if (!user.getBlocked()) {
            userService.saveUser(user, "user", false);
        } else {
            userService.saveUser(user, "noRole", false);
        }

        return "redirect:/admin/users";
    }

    @RequestMapping("/change-user-password/{userId}")
    public String changeUserPassword(Model model, @PathVariable("userId") Long instId) {
        User user = userService.findById(instId);
        model.addAttribute("user", user);
        return "admin-users/change-user-password";
    }

    @PostMapping("/change-user-password-action")
    public String changeUserPasswordAction(Model model, @RequestParam("password2") String pass2,
                                           @ModelAttribute("user") @Validated({UserChangePassword.class}) User user,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-users/change-user-password";
        }

        if (!user.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "admin-users/change-user-password";
        }

        userService.saveUser(user, "user", true);

        return "redirect:/admin/edit-user/" + user.getId() + "?passwordChanged=yes";
    }

    @RequestMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "admin-users/add-user";
    }

    @PostMapping("/add-user-action")
    public String addUserAction(Model model, @RequestParam("password2") String pass2,
                                @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-users/add-user";
        }

        if (!user.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "admin-users/add-user";
        }

        user.setBlocked(false);
        userService.saveUser(user, "user", true);

        model.addAttribute("passNoMatch", false);

        return "redirect:/admin/users";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.findUsers();
    }
}

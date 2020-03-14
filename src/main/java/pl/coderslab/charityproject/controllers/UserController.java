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
public class UserController {

    private final UserService userService;

    @RequestMapping("/users")
    public String userList() {
        return "/users/users-list";
    }

    @RequestMapping("/delete-user/{instId}")
    public String deleteUserAction(@PathVariable("instId") Long instId) {
        User user = userService.findById(instId);
        userService.deleteUser(user);

        return "redirect:/admin/users";
    }

    @RequestMapping("/edit-user/{adminId}")
    public String editUser(Model model, @PathVariable("adminId") Long instId) {
        User user = userService.findById(instId);
        model.addAttribute("user", user);
        return "/users/edit-user";
    }

    @PostMapping("/edit-user-action")
    public String editUserAction(Model model, @RequestParam("password2") String pass2,
                                        @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/edit-user";
        }

        if (!user.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "/users/add-user";
        }

        userService.saveAdmin(user);

        return "redirect:/admin/users";
    }

    @RequestMapping("/add-user")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "/users/add-user";
    }

    @PostMapping("/add-user-action")
    public String addUserAction(Model model, @RequestParam("password2") String pass2,
                                 @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/users/add-user";
        }

        if (!user.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "/users/add-user";
        }

        userService.saveUser(user);

        model.addAttribute("passNoMatch", false);

        return "redirect:/admin/users";
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.findUsers();
    }
}

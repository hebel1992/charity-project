package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.services.UserService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginRegisterController {

    private final UserService userService;

    @RequestMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register-action")
    public String registerAction(Model model, @RequestParam("password2") String pass2, @ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (!user.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "register";
        }

        userService.saveUser(user);

        model.addAttribute("passNoMatch", false);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginError", false);
        return "login";
    }

    @GetMapping("/login-error")
    public String loginErrorPage(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}

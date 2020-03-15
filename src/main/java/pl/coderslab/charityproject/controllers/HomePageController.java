package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charityproject.models.*;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;
import pl.coderslab.charityproject.validationGroups.EditedUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final DonationService donationService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String donateAction(Model model, @RequestParam("formSuccess") Boolean success) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("formSuccess", success);
        return "form";
    }

    @PostMapping("/donate-execute")
    public String donateActionExecute(@AuthenticationPrincipal CurrentUser currentUser,
                                      @ModelAttribute("donation") @Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        donation.setUser(currentUser.getUser());
        donationService.saveDonation(donation);

        return "redirect:home?formSuccess=true";
    }

    @RequestMapping("/edit-account")
    public String editAccount(Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("user", currentUser.getUser());
        return "edit-account";
    }

    @PostMapping("/edit-account-action")
    public String editAccountAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @ModelAttribute("user") @Validated({EditedUser.class})User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "edit-account";
        }

        user.setPassword(currentUser.getUser().getPassword());

        userService.saveUser(user, "user", false);

        return "redirect:home?formSuccess=false";
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

}

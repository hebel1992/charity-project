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
import pl.coderslab.charityproject.validationGroups.UserChangingPassword;
import pl.coderslab.charityproject.validationGroups.UserCreatingDonation;

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
    public String donateAction(Model model, @RequestParam(value = "formSuccess", required = false) String success,
                               @RequestParam(value = "passwordChanged", required = false) String passwordChanged,
                               @RequestParam(value = "accountUpdated", required = false) String accountUpdated) {
        model.addAttribute("donation", new Donation());
        model.addAttribute("formSuccess", success);
        model.addAttribute("passwordChanged", passwordChanged);
        model.addAttribute("accountUpdated", accountUpdated);
        return "form";
    }

    @PostMapping("/donate-execute")
    public String donateActionExecute(@AuthenticationPrincipal CurrentUser currentUser,
                                      @ModelAttribute("donation") @Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        donation.setUser(currentUser.getUser());
        donation.setStatus(Status.nieodebrana);
        donationService.saveDonation(donation);

        return "redirect:/home?formSuccess=yes";
    }

    @RequestMapping("/edit-account")
    public String editAccount(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("user", currentUser.getUser());
        return "edit-account";
    }

    @PostMapping("/edit-account-action")
    public String editAccountAction(@AuthenticationPrincipal CurrentUser currentUser,
                                    @ModelAttribute("user") @Validated({EditedUser.class}) User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-account";
        }

        user.setPassword(currentUser.getUser().getPassword());

        userService.saveUser(user, "user", false);

        return "redirect:/home?accountUpdated=yes";
    }


    @RequestMapping("/change-password/{userId}")
    public String changePassword(Model model, @PathVariable("userId") Long instId) {
        User user = userService.findById(instId);
        model.addAttribute("user", user);
        return "change-password";
    }

    @PostMapping("/change-password-action")
    public String changePasswordAction(Model model, @AuthenticationPrincipal CurrentUser currentUser, @RequestParam("password2") String pass2,
                                       @RequestParam("oldPassword") String oldPassword,
                                       @ModelAttribute("user") @Validated({UserChangingPassword.class}) User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "change-password";
        }

        if (!user.getPassword().equals(pass2)) {
            model.addAttribute("passNoMatch", true);
            return "change-password";
        }

        if (!passwordEncoder.matches(oldPassword, currentUser.getUser().getPassword())) {
            model.addAttribute("wrongOldPass", true);
            return "change-password";
        }

        model.addAttribute("passwordChanged", true);

        userService.saveUser(user, "user", true);

        return "redirect:/home?passwordChanged=yes";
    }

    @RequestMapping("/donations-list")
    public String donationList(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("donations", donationService.findByUser(currentUser.getUser().getId()));
        return "donations-list";
    }

    @RequestMapping("/donation-details/{donationId}")
    public String donationDetails(Model model, @PathVariable("donationId") Long donationId) {
        model.addAttribute("donation", donationService.findById(donationId));
        return "donation-details";
    }

    @RequestMapping("/change-status/{donationId}")
    public String changeStatus(Model model, @PathVariable("donationId") Long donationId) {
        model.addAttribute("donation", donationService.findById(donationId));
        return "change-status";
    }

    @PostMapping("/change-status-action")
    public String changeStatusAction(Model model, @ModelAttribute @Valid Donation donation,
                                     BindingResult bindingResult) {

        if(donation.getActualPickUpDate()==null){
            model.addAttribute("dateRequired", "dateRequired");
            return "change-status";
        }

        if(bindingResult.hasErrors()){
            return "change-status";
        }

        donation.setStatus(Status.odebrana);
        donationService.saveDonation(donation);

        return "redirect:/donation-details/"+donation.getId();
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

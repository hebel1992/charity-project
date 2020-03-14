package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charityproject.models.Category;
import pl.coderslab.charityproject.models.CurrentUser;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomePageController {
    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final DonationService donationService;

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

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAll();
    }

}

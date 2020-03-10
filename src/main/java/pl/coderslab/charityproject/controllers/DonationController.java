package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Category;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DonationController {
    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    @RequestMapping("/donate")
    public String donateAction(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }

    @PostMapping("/donate-execute")
    public String donateActionExecute(@ModelAttribute("donation") @Valid Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        return "redirect:/";
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

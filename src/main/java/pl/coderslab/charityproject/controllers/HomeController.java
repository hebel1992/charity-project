package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.models.User;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final InstitutionService institutionService;
    private final DonationService donationService;

    @RequestMapping("/")
    public String homeAction() {
        return "index";
    }

    @RequestMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register-action")
    public String registerAction(){
        return "redirect:/home";
    }

    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.findAll();
    }

    @ModelAttribute("donations")
    public List<Donation> getAllDonations() {
        return donationService.findAll();
    }

    @ModelAttribute("bags")
    public Integer getAllBags() {
        return donationService.getAllBags();
    }
}



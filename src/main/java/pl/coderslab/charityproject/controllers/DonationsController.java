package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.*;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class DonationsController {

    private final DonationService donationService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final InstitutionService institutionService;

    @RequestMapping("/donations")
    public String donationsList() {
        return "admin-donations/donations-list";
    }

    @RequestMapping("/delete-donation/{donId}")
    public String deleteDonationAction(@PathVariable("donId") Long donId) {
        Donation donation = donationService.findById(donId);
        donationService.deleteDonation(donation);

        return "redirect:/admin/donations";
    }

    @RequestMapping("/edit-donation/{donId}")
    public String editDonation(Model model, @PathVariable("donId") Long donId) {
        Donation donation = donationService.findById(donId);
        model.addAttribute("donation", donation);
        return "admin-donations/edit-donation";
    }

    @PostMapping("/edit-donation-action")
    public String editDonationAction(@ModelAttribute("donation") @Valid Donation donation,
                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-donations/edit-donation";
        }

        return "redirect:/admin/donations";
    }


    @RequestMapping("/add-donation")
    public String addDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "admin-donations/add-donation";
    }

    @PostMapping("/add-donation-action")
    public String addDonationAction(@ModelAttribute("donation") @Valid Donation donation,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-donations/add-donation";
        }

        donation.setStatus(Status.nieodebrana);
        donationService.saveDonation(donation);

        return "redirect:/admin/donations";
    }

    @ModelAttribute("donations")
    public List<Donation> donations() {
        return donationService.findAll();
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("users")
    public List<User> users() {
        return userService.findUsers();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }
}

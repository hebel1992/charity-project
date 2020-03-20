package pl.coderslab.charityproject.controllers.adminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.*;
import pl.coderslab.charityproject.services.CategoryService;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;
import pl.coderslab.charityproject.validationGroups.AdminCreatingDonation;
import pl.coderslab.charityproject.validationGroups.UserCreatingDonation;

import javax.validation.Valid;
import javax.validation.groups.Default;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class DonationsController extends AbstractController {

    private final CategoryService categoryService;


    public DonationsController(DonationService donationService, CategoryService categoryService,
                               UserService userService, InstitutionService institutionService) {
        super(donationService, userService, institutionService);
        this.categoryService = categoryService;
    }

    @RequestMapping("/donations")
    public String donationsList() {
        return "admin-donations/donations-list";
    }

    @RequestMapping("/delete-donation/{donationId}")
    public String deleteDonationAction(@PathVariable("donationId") Long donationId) {
        Donation donation = donationService.findById(donationId);
        donationService.deleteDonation(donation);

        return "redirect:/admin/donations";
    }

    @RequestMapping("/edit-donation/{donationId}")
    public String editDonation(Model model, @PathVariable("donationId") Long donationId) {
        Donation donation = donationService.findById(donationId);
        model.addAttribute("donation", donation);
        return "admin-donations/edit-donation";
    }

    @PostMapping("/edit-donation-action")
    public String editDonationAction(Model model,
                                     @ModelAttribute("donation") @Validated({Default.class, AdminCreatingDonation.class}) Donation donation, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-donations/edit-donation";
        }

        if (donation.getStatus() == Status.odebrana && donation.getActualPickUpDate() == null) {
            model.addAttribute("dateRequired", "dateRequired");
            return "admin-donations/edit-donation";
        }

        donationService.saveDonation(donation);

        return "redirect:/admin/donations";
    }


    @RequestMapping("/add-donation")
    public String addDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "admin-donations/add-donation";
    }

    @PostMapping("/add-donation-action")
    public String addDonationAction(@ModelAttribute("donation") @Validated({Default.class, AdminCreatingDonation.class}) Donation donation,
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

    @ModelAttribute("statuses")
    public List<Status> statuses() {
        return Arrays.asList(Status.values());
    }
}

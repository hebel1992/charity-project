package pl.coderslab.charityproject.controllers.adminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.coderslab.charityproject.models.Donation;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;

@Controller
public class AbstractController {

    final DonationService donationService;
    final UserService userService;
    final InstitutionService institutionService;

    public AbstractController(DonationService donationService, UserService userService, InstitutionService institutionService) {
        this.donationService = donationService;
        this.userService = userService;
        this.institutionService = institutionService;
    }

    @ModelAttribute("countDonations")
    public Integer countDonations() {
        return donationService.findAll().size();
    }

    @ModelAttribute("countBags")
    public Integer countBags() {
        int bags = 0;
        for (Donation d : donationService.findAll()) {
            bags += d.getQuantity();
        }
        return bags;
    }

    @ModelAttribute("countUsers")
    public Integer countUsers() {
        return userService.findUsers().size();
    }

    @ModelAttribute("countInstitutions")
    public Integer countInstitutions(){
        return institutionService.findAll().size();
    }
}

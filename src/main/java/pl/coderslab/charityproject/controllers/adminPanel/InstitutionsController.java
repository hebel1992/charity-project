package pl.coderslab.charityproject.controllers.adminPanel;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.services.DonationService;
import pl.coderslab.charityproject.services.InstitutionService;
import pl.coderslab.charityproject.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class InstitutionsController extends AbstractController {

    public InstitutionsController(DonationService donationService, InstitutionService institutionService,
                                  UserService userService) {
        super(donationService, userService, institutionService);
    }

    @RequestMapping("/institutions")
    public String institutionsList() {
        return "admin-institutions/institutions-list";
    }

    @RequestMapping("/delete-institution/{instId}")
    public String deleteInstitutionAction(@PathVariable("instId") Long instId) {
        Institution institution = institutionService.findById(instId);
        institutionService.deleteInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @RequestMapping("/edit-institution/{instId}")
    public String editInstitution(Model model, @PathVariable("instId") Long instId) {
        Institution institution = institutionService.findById(instId);
        model.addAttribute("institution", institution);
        return "admin-institutions/edit-institution";
    }

    @PostMapping("/edit-institution-action")
    public String editInstitutionAction(@ModelAttribute("institution") @Valid Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-institutions/edit-institution";
        }
        institutionService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @RequestMapping("/add-institution")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin-institutions/add-institution";
    }

    @PostMapping("/add-institution-action")
    public String addInstitutionAction(@ModelAttribute("institution") @Valid Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin-institutions/add-institution";
        }
        institutionService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }
}

package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.services.InstitutionService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;

    @RequestMapping
    public String mainPage() {
        return "/admin/admin-main";
    }

    @RequestMapping("/institutions")
    public String institutionsList() {
        return "/admin/institutions";
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
        return "/admin/edit-institution";
    }

    @RequestMapping("/edit-institution-action")
    public String editInstitutionAction(@ModelAttribute("institution") @Valid Institution institution, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/admin/edit-institution";
        }
        institutionService.saveInstitution(institution);

        return "redirect:/admin/institutions";
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }
}

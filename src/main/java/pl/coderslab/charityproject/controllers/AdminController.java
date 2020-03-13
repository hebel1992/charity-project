package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.services.InstitutionService;

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


    @ModelAttribute("institutions")
    public List<Institution> institutions() {
        return institutionService.findAll();
    }
}

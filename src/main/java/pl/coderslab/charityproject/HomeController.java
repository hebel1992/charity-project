package pl.coderslab.charityproject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charityproject.models.Institution;
import pl.coderslab.charityproject.services.InstitutionService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final InstitutionService institutionService;

    @RequestMapping("/")
    public String homeAction() {
        return "index";
    }

    @ModelAttribute("institutions")
    public List<Institution> getAllInstitutions() {
        return institutionService.findAll();
    }
}



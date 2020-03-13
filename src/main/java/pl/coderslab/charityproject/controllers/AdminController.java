package pl.coderslab.charityproject.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping
    public String mainPage(){
        return "admin/main";
    }
}

package com.elibrary.elibraryapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/showViewPage")
    public String passParametersWithModel(Model model) {
        model.addAttribute("message", "Welcome to spring application");
        return "view/viewPage";
    }
}

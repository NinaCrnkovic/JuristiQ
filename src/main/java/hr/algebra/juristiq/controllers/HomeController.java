package hr.algebra.juristiq.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/JuristiQ")
public class HomeController {

    @GetMapping("/home")
    public String showHomePage(Model model) {

        return "home";
    }
}

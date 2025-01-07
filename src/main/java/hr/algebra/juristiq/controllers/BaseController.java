package hr.algebra.juristiq.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class BaseController {

    @ModelAttribute
    public void addAuthenticationAttributes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated() &&
                !(authentication.getPrincipal() instanceof String);
        model.addAttribute("isAuthenticated", isAuthenticated);
    }
}



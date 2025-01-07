package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.Action;
import hr.algebra.juristiq.models.Lawyer;
import hr.algebra.juristiq.models.User;
import hr.algebra.juristiq.services.ActionService;
import hr.algebra.juristiq.services.LawyerService;
import hr.algebra.juristiq.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/JuristiQ")
@AllArgsConstructor
public class HomeController {

    private final UserService userService;
    private final LawyerService lawyerService;
    private final ActionService actionService;

    @GetMapping
    public String showWelcomePage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal() instanceof String) {
            return "auth_pages/welcome"; // Prikazuje stranicu dobrodošlice za neprijavljene korisnike
        }
        return "redirect:/JuristiQ/home"; // Preusmjerava prijavljene korisnike
    }

    @GetMapping("/auth/login")
    public String showLoginPage() {
        return "auth_pages/login"; // Prilagođena stranica prijave
    }

    @GetMapping("/home")
    public String showHomePage(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            Lawyer lawyer = lawyerService.findByEmail(email);

            if (lawyer != null) {
                List<Action> lawyerActions = actionService.findActionsByLawyerEmail(email);
                model.addAttribute("lawyer", lawyer);
                model.addAttribute("actions", lawyerActions);
            }
        }
        return "home";
    }





    @GetMapping("/auth/register-lawyer")
    public String showRegisterLawyerPage() {
        return "auth_pages/register-lawyer";
    }

    @GetMapping("/auth/register-law-firm")
    public String showRegisterLawFirmPage() {
        return "auth_pages/register-law-firm";
    }

    @GetMapping("/auth/welcome")
    public String showWelcomeAfterLogout() {
        return "auth_pages/welcome"; // Stranica dobrodošlice nakon odjave
    }


}

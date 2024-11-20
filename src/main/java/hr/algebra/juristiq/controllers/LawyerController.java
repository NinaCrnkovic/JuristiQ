package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.Lawyer;
import hr.algebra.juristiq.services.LawFirmService;
import hr.algebra.juristiq.services.LawyerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JuristiQ/lawyers")
@AllArgsConstructor
public class LawyerController {

    private final LawyerService lawyerService;
    private final LawFirmService lawFirmService;

    @GetMapping
    public String getAllLawyers(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Lawyer> lawyers = (query == null || query.isEmpty()) ?
                lawyerService.getAllLawyers() :
                lawyerService.searchLawyers(query);
        model.addAttribute("lawyers", lawyers);
        model.addAttribute("query", query); // Retain the search term in the UI
        return "lawyer_pages/list-lawyers";
    }

    @GetMapping("/add")
    public String showAddLawyerForm(Model model) {
        model.addAttribute("lawyer", new Lawyer());
        model.addAttribute("lawFirms", lawFirmService.getAllLawFirms());
        return "lawyer_pages/add-lawyer";
    }

    @PostMapping("/add")
    public String addLawyer(@ModelAttribute Lawyer lawyer) {
        lawyerService.saveLawyer(lawyer);
        return "redirect:/JuristiQ/lawyers"; // Preusmjerava na popis odvjetnika
    }

    @GetMapping("/edit/{id}")
    public String showEditLawyerForm(@PathVariable Long id, Model model) {
        Lawyer lawyer = lawyerService.getLawyerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid lawyer ID: " + id));
        model.addAttribute("lawyer", lawyer);
        model.addAttribute("lawFirms", lawFirmService.getAllLawFirms());
        return "lawyer_pages/edit-lawyer";
    }

    @PostMapping("/edit/{id}")
    public String editLawyer(@PathVariable Long id, @ModelAttribute Lawyer lawyer) {
        lawyer.setId(id); // Osigurava da se postojeći odvjetnik ažurira
        lawyerService.saveLawyer(lawyer);
        return "redirect:/JuristiQ/lawyers"; // Preusmjerava na popis odvjetnika
    }

    @GetMapping("/delete/{id}")
    public String deleteLawyer(@PathVariable Long id) {
        lawyerService.deleteLawyer(id);
        return "redirect:/JuristiQ/lawyers"; // Preusmjerava na popis odvjetnika
    }
}

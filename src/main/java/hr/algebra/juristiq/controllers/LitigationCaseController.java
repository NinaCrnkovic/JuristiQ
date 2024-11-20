package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.services.ClientService;
import hr.algebra.juristiq.services.LitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import hr.algebra.juristiq.enums.Court;


@Controller
@RequestMapping("/JuristiQ/litigation-cases")
@AllArgsConstructor
public class LitigationCaseController {

    private final LitigationCaseService litigationCaseService;
    private final ClientService clientService;


    @GetMapping("/add")
    public String showAddLitigationCaseForm(Model model) {
        model.addAttribute("litigationCase", new LitigationCase());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("courts", Court.values());
        return "case_pages/add-litigation-case";
    }


    @PostMapping("/add")
    public String addLitigationCase(@ModelAttribute LitigationCase litigationCase) {
        litigationCaseService.saveLitigationCase(litigationCase);
        return "redirect:/JuristiQ/cases";
    }

    @GetMapping("/edit/{id}")
    public String showEditLitigationCaseForm(@PathVariable Long id, Model model) {
        LitigationCase litigationCase = litigationCaseService.getLitigationCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Litigation Case ID: " + id));
        model.addAttribute("litigationCase", litigationCase);
        return "case_pages/edit-litigation-case";
    }

    @PostMapping("/edit/{id}")
    public String editLitigationCase(@PathVariable Long id, @ModelAttribute LitigationCase litigationCase) {
        litigationCase.setId(id);
        litigationCaseService.saveLitigationCase(litigationCase);
        return "redirect:/JuristiQ/cases";
    }
}


package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.NonLitigationCase;
import hr.algebra.juristiq.services.NonLitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/JuristiQ/non-litigation-cases")
@AllArgsConstructor
public class NonLitigationCaseController {

    private final NonLitigationCaseService nonLitigationCaseService;

    @GetMapping("/add")
    public String showAddNonLitigationCaseForm(Model model) {
        model.addAttribute("nonLitigationCase", new NonLitigationCase());
        return "case_pages/add-non-litigation-case";
    }

    @PostMapping("/add")
    public String addNonLitigationCase(@ModelAttribute NonLitigationCase nonLitigationCase) {
        nonLitigationCaseService.saveNonLitigationCase(nonLitigationCase);
        return "redirect:/JuristiQ/cases";
    }

    @GetMapping("/edit/{id}")
    public String showEditNonLitigationCaseForm(@PathVariable Long id, Model model) {
        NonLitigationCase nonLitigationCase = nonLitigationCaseService.getNonLitigationCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Non-Litigation Case ID: " + id));
        model.addAttribute("nonLitigationCase", nonLitigationCase);
        return "case_pages/edit-non-litigation-case";
    }

    @PostMapping("/edit/{id}")
    public String editNonLitigationCase(@PathVariable Long id, @ModelAttribute NonLitigationCase nonLitigationCase) {
        nonLitigationCase.setId(id);
        nonLitigationCaseService.saveNonLitigationCase(nonLitigationCase);
        return "redirect:/JuristiQ/cases";
    }
}
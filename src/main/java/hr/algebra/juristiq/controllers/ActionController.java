package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.models.Action;

import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.services.ActionService;
import hr.algebra.juristiq.services.CostCalculationService;
import hr.algebra.juristiq.services.LitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/JuristiQ/actions")
@AllArgsConstructor

public class ActionController {

    private final ActionService actionService;
    private final LitigationCaseService litigationCaseService;
    private final CostCalculationService costCalculationService;



    @PostMapping("/add")
    public String addAction(@ModelAttribute Action action, @RequestParam Long caseId) {
        System.out.println("Adding action to case with ID: " + caseId);

        // Dohvati predmet (LitigationCase) za koji se radnja dodaje
        LitigationCase litigationCase = litigationCaseService.getLitigationCaseById(caseId)
                .orElseThrow(() -> new RuntimeException("Litigation case not found"));

        // Izračunaj trošak radnje
        double calculatedCost = 0.0;
        if (litigationCase.getCaseType() != null) {
            calculatedCost = costCalculationService.calculateLitigationCost(
                    action.getType(),
                    litigationCase.getCaseType(),
                    litigationCase.getVps()
            );
        }
        action.setAmount(calculatedCost);

        // Dodaj radnju na predmet
        actionService.addActionToCase(caseId, action);
        return "redirect:/JuristiQ/litigation-cases/details/" + caseId;
    }



    @GetMapping("/add")
    public String showAddActionForm(@RequestParam Long caseId, Model model) {
        model.addAttribute("caseId", caseId);
        model.addAttribute("action", new Action());
        model.addAttribute("action", ActionType.values());
        return "action_pages/add-action";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable Long id, Model model) {
        var litigationCase = litigationCaseService.getLitigationCaseById(id)
                .orElseThrow(() -> new RuntimeException("Litigation case not found"));
        model.addAttribute("litigationCase", litigationCase);
        return "case_pages/details-litigation-case"; // Match this with your Thymeleaf template path
    }

    @GetMapping("/edit/{id}")
    public String showEditActionForm(@PathVariable Long id, Model model) {
        Action action = actionService.getActionById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));
        model.addAttribute("action", action); // Proslijedi cijeli objekt 'Action'
        model.addAttribute("actionTypes", ActionType.values()); // Dodaj dostupne tipove akcija
        return "action_pages/edit-action";
    }

    @PostMapping("/edit")
    public String editAction(@ModelAttribute Action action) {
        // Ensure LitigationCase is managed
        if (action.getLitigationCase() != null && action.getLitigationCase().getId() != null) {
            LitigationCase litigationCase = litigationCaseService.getLitigationCaseById(action.getLitigationCase().getId())
                    .orElseThrow(() -> new RuntimeException("Litigation case not found"));
            action.setLitigationCase(litigationCase);
        }

        // Save action
        actionService.saveAction(action);

        // Redirect to litigation case details page
        return "redirect:/JuristiQ/litigation-cases/details/" + action.getLitigationCase().getId();
    }

    @GetMapping("/delete")
    public String deleteAction(@RequestParam Long actionId, @RequestParam Long caseId) {
        actionService.deleteAction(actionId);
        return "redirect:/litigation-cases/details/" + caseId;
    }


}

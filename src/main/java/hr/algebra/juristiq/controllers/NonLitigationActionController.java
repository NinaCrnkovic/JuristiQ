package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.NonLitigationActionType;
import hr.algebra.juristiq.models.NonLitigationAction;
import hr.algebra.juristiq.models.NonLitigationCase;
import hr.algebra.juristiq.services.CostCalculationService;
import hr.algebra.juristiq.services.NonLitigationActionService;
import hr.algebra.juristiq.services.NonLitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/JuristiQ/non-litigation-actions")
@AllArgsConstructor
public class NonLitigationActionController {

    private final NonLitigationActionService nonLitigationActionService;
    private final NonLitigationCaseService nonLitigationCaseService;
    private final CostCalculationService costCalculationService;

    @PostMapping("/add")
    public String addAction(@ModelAttribute NonLitigationAction action, @RequestParam Long caseId) {
        NonLitigationCase nonLitigationCase = nonLitigationCaseService.getNonLitigationCaseById(caseId)
                .orElseThrow(() -> new RuntimeException("Non-Litigation case not found"));

        double calculatedCost = costCalculationService.calculateNonLitigationCost(
                action.getType(),
                nonLitigationCase.getCaseType(),
                nonLitigationCase.getVps()
        );

        action.setAmount(calculatedCost);
        action.setNonLitigationCase(nonLitigationCase);
        nonLitigationActionService.addActionToCase(caseId, action);
        return "redirect:/JuristiQ/non-litigation-cases/details/" + caseId;
    }

    @GetMapping("/add")
    public String showAddActionForm(@RequestParam Long caseId, Model model) {
        model.addAttribute("caseId", caseId);
        model.addAttribute("action", new NonLitigationAction());
        model.addAttribute("actionTypes", NonLitigationActionType.values());
        return "action_pages/add-non-litigation-action";
    }

    @GetMapping("/edit/{id}")
    public String showEditActionForm(@PathVariable Long id, Model model) {
        NonLitigationAction action = nonLitigationActionService.getActionById(id)
                .orElseThrow(() -> new RuntimeException("Action not found"));
        model.addAttribute("action", action);
        model.addAttribute("actionTypes", NonLitigationActionType.values());
        return "action_pages/edit-non-litigation-action";
    }

    @PostMapping("/edit")
    public String editAction(@ModelAttribute NonLitigationAction action) {
        nonLitigationActionService.saveAction(action);
        return "redirect:/JuristiQ/non-litigation-cases/details/" + action.getNonLitigationCase().getId();
    }

    @GetMapping("/delete")
    public String deleteAction(@RequestParam Long actionId, @RequestParam Long caseId) {
        nonLitigationActionService.deleteAction(actionId);
        return "redirect:/JuristiQ/non-litigation-cases/details/" + caseId;
    }
}


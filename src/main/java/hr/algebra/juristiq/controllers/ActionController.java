package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.models.Action;

import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.services.ActionService;
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



    @PostMapping("/add")
    public String addAction(@ModelAttribute Action action, @RequestParam Long caseId) {
        System.out.println("Adding action to case with ID: " + caseId);
        actionService.addActionToCase(caseId, action);
        return "redirect:/JuristiQ/litigation-cases/details/" + caseId;
    }


    @GetMapping("/add")
    public String showAddActionForm(@RequestParam Long caseId, Model model) {
        model.addAttribute("caseId", caseId);
        model.addAttribute("action", new Action());
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
        model.addAttribute("action", action);
        return "action_pages/edit-action"; // Match with your edit-action.html template
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

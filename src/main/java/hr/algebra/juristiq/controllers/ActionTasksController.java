package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.models.Action;
import hr.algebra.juristiq.models.NonLitigationAction;
import hr.algebra.juristiq.services.ActionService;
import hr.algebra.juristiq.services.NonLitigationActionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/JuristiQ/actions-task")
@AllArgsConstructor
public class ActionTasksController {

    private final ActionService actionService;
    private final NonLitigationActionService nonLitigationActionService;

    @GetMapping
    public String listActions(Model model) {
        // Fetch all litigation actions
        List<Action> litigationActions = actionService.getAllActions();

        // Group litigation actions into hearings and other actions
        List<Action> hearings = litigationActions.stream()
                .filter(action -> action.getType() == ActionType.PRISUSTVOVANJE_ROČIŠTU || action.getType() == ActionType.ZASTUPANJE_PRED_SUDOM)
                .collect(Collectors.toList());
        List<Action> otherLitigationActions = litigationActions.stream()
                .filter(action -> action.getType() != ActionType.PRISUSTVOVANJE_ROČIŠTU && action.getType() != ActionType.ZASTUPANJE_PRED_SUDOM)
                .collect(Collectors.toList());

        // Fetch non-litigation actions
        List<NonLitigationAction> nonLitigationActions = nonLitigationActionService.getAllActions();

        hearings.sort(Comparator.comparing(Action::getDate).thenComparing(Action::getTime));
        otherLitigationActions.sort(Comparator.comparing(Action::getDate).thenComparing(Action::getTime));
        nonLitigationActions.sort(Comparator.comparing(NonLitigationAction::getDate).thenComparing(NonLitigationAction::getTime));

        // Prosljeđivanje sortirane liste u model
        model.addAttribute("hearings", hearings);
        model.addAttribute("otherLitigationActions", otherLitigationActions);
        model.addAttribute("nonLitigationActions", nonLitigationActions);

        return "task_pages/actions-task"; // The view will be a single page showing all actions
    }
}

package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.ActionType;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.enums.NonLitigationActionType;
import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.models.CostCalculation;
import hr.algebra.juristiq.services.CostCalculationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
@Controller
@RequestMapping("/JuristiQ/cost-calculator")
@AllArgsConstructor
public class CostCalculatorController {

    private final CostCalculationService costCalculationService;

    // Prikaz stranice za sudske troškove
    @GetMapping("/litigation")
    public String showLitigationCostPage(Model model) {
        model.addAttribute("litigationActionTypes", ActionType.values());
        model.addAttribute("litigationCaseTypes", LitigationCaseType.values());
        return "calculator_pages/litigation-cost-calculator";
    }

    // Prikaz stranice za izvanparnične troškove
    @GetMapping("/non-litigation")
    public String showNonLitigationCostPage(Model model) {
        model.addAttribute("nonLitigationActionTypes", NonLitigationActionType.values());
        model.addAttribute("nonLitigationCaseTypes", NonLitigationCaseType.values());
        return "calculator_pages/non-litigation-cost-calculator";
    }

    // Izračun za sudske troškove
    @PostMapping("/litigation")
    public String calculateLitigationCost(
            @RequestParam ActionType actionType,
            @RequestParam LitigationCaseType caseType,
            @RequestParam double vps,
            Model model) {

        double cost = costCalculationService.calculateLitigationCost(actionType, caseType, vps);
        model.addAttribute("calculatedCost", cost);
        model.addAttribute("actionType", actionType);
        model.addAttribute("caseType", caseType);
        model.addAttribute("vps", vps);

        return "calculator_pages/litigation-cost-calculator";
    }

    // Izračun za izvanparnične troškove
    @PostMapping("/non-litigation")
    public String calculateNonLitigationCost(
            @RequestParam NonLitigationActionType actionType,
            @RequestParam NonLitigationCaseType caseType,
            @RequestParam double vps,
            Model model) {

        double cost = costCalculationService.calculateNonLitigationCost(actionType, caseType, vps);
        model.addAttribute("calculatedCost", cost);
        model.addAttribute("actionType", actionType);
        model.addAttribute("caseType", caseType);
        model.addAttribute("vps", vps);

        return "calculator_pages/non-litigation-cost-calculator";
    }
}

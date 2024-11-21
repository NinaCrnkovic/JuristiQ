package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.NonLitigationCaseType;
import hr.algebra.juristiq.models.Client;
import hr.algebra.juristiq.models.NonLitigationCase;
import hr.algebra.juristiq.services.ClientService;
import hr.algebra.juristiq.services.NonLitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JuristiQ/non-litigation-cases")
@AllArgsConstructor
public class NonLitigationCaseController {

    private final NonLitigationCaseService nonLitigationCaseService;
    private final ClientService clientService;


    @GetMapping
    public String listNonLitigationCases(@RequestParam(value = "search", required = false) String search, Model model) {
        List<NonLitigationCase> nonLitigationCases = (search != null && !search.isEmpty())
                ? nonLitigationCaseService.searchNonLitigationCases(search)
                : nonLitigationCaseService.getAllNonLitigationCases();
        model.addAttribute("nonLitigationCases", nonLitigationCases);
        model.addAttribute("search", search);
        return "case_pages/list-non-litigation-cases";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nonLitigationCase", new NonLitigationCase());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("caseTypes", NonLitigationCaseType.values());
        return "case_pages/add-non-litigation-case";
    }

    @PostMapping("/add")
    public String addNonLitigationCase(
            @ModelAttribute NonLitigationCase nonLitigationCase,
            @RequestParam("representedParties") List<Long> representedIds,
            @RequestParam("opposingParties") List<Long> opposingIds) {

        List<Client> representedParties = clientService.getClientsByIds(representedIds);
        List<Client> opposingParties = clientService.getClientsByIds(opposingIds);

        nonLitigationCase.setRepresentedParties(representedParties);
        nonLitigationCase.setOpposingParties(opposingParties);

        nonLitigationCaseService.saveNonLitigationCase(nonLitigationCase);
        return "redirect:/JuristiQ/non-litigation-cases";
    }

    @GetMapping("/edit/{id}")
    public String showEditNonLitigationCaseForm(@PathVariable Long id, Model model) {
        NonLitigationCase nonLitigationCase = nonLitigationCaseService.getNonLitigationCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Non-Litigation Case ID: " + id));
        model.addAttribute("nonLitigationCase", nonLitigationCase);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("caseTypes", NonLitigationCaseType.values());
        return "case_pages/edit-non-litigation-case";
    }

    @PostMapping("/edit/{id}")
    public String editNonLitigationCase(@PathVariable Long id, @ModelAttribute NonLitigationCase nonLitigationCase) {
        nonLitigationCase.setId(id);
        nonLitigationCaseService.saveNonLitigationCase(nonLitigationCase);
        return "redirect:/JuristiQ/non-litigation-cases";
    }

    @GetMapping("/delete/{id}")
    public String deleteNonLitigationCase(@PathVariable Long id) {
        nonLitigationCaseService.deleteNonLitigationCase(id);
        return "redirect:/JuristiQ/non-litigation-cases";
    }

    @GetMapping("/{id}/details")
    public String showNonLitigationCaseDetails(@PathVariable Long id, Model model) {
        NonLitigationCase nonLitigationCase = nonLitigationCaseService.getNonLitigationCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Non-Litigation Case ID: " + id));
        model.addAttribute("nonLitigationCase", nonLitigationCase);
        return "case_pages/details-non-litigation-case";
    }

    @GetMapping("/details/{id}")
    public String getNonLitigationCaseDetails(@PathVariable Long id, Model model) {
        NonLitigationCase caseDetails = nonLitigationCaseService.findById(id);

        model.addAttribute("nonLitigationCase", caseDetails);
        return "case_pages/details-non-litigation-case"; // Ovaj naziv mora odgovarati nazivu HTML datoteke.
    }
}

package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.Court;
import hr.algebra.juristiq.models.Client;
import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.services.ClientService;
import hr.algebra.juristiq.services.LitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JuristiQ/litigation-cases")
@AllArgsConstructor
public class LitigationCaseController {

    private final LitigationCaseService litigationCaseService;
    private final ClientService clientService;

    // List all litigation cases
    @GetMapping
    public String listLitigationCases(@RequestParam(value = "search", required = false) String search, Model model) {
        List<LitigationCase> litigationCases = (search != null && !search.isEmpty())
                ? litigationCaseService.searchLitigationCases(search)
                : litigationCaseService.getAllLitigationCases();
        model.addAttribute("litigationCases", litigationCases);
        model.addAttribute("search", search);
        return "case_pages/list-litigation-cases";
    }

    // Show the add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("litigationCase", new LitigationCase());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("courts", Court.values());
        return "case_pages/add-litigation-case";
    }

    @PostMapping("/add")
    public String addLitigationCase(
            @ModelAttribute LitigationCase litigationCase,
            @RequestParam("representedParties") List<Long> representedIds,
            @RequestParam("opposingParties") List<Long> opposingIds) {

        List<Client> representedParties = clientService.getClientsByIds(representedIds);
        List<Client> opposingParties = clientService.getClientsByIds(opposingIds);

        litigationCase.setRepresentedParties(representedParties);
        litigationCase.setOpposingParties(opposingParties);

        litigationCaseService.saveLitigationCase(litigationCase);
        return "redirect:/JuristiQ/litigation-cases";
    }

    // Dynamic search for clients
    @GetMapping("/clients/search")
    @ResponseBody
    public List<Client> searchClients(@RequestParam String query) {
        return clientService.searchClientsByName(query);
    }


    @GetMapping("/edit/{id}")
    public String showEditLitigationCaseForm(@PathVariable Long id, Model model) {
        LitigationCase litigationCase = litigationCaseService.getLitigationCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Litigation Case ID: " + id));
        model.addAttribute("litigationCase", litigationCase);
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("courts", Court.values());
        return "case_pages/edit-litigation-case";
    }


    @PostMapping("/edit/{id}")
    public String editLitigationCase(@PathVariable Long id, @ModelAttribute LitigationCase litigationCase) {
        litigationCase.setId(id);
        litigationCaseService.saveLitigationCase(litigationCase);
        return "redirect:/JuristiQ/litigation-cases";
    }


    @GetMapping("/delete/{id}")
    public String deleteLitigationCase(@PathVariable Long id) {
        litigationCaseService.deleteLitigationCase(id);
        return "redirect:/JuristiQ/litigation-cases";
    }


    @GetMapping("/{id}/details")
    public String showLitigationCaseDetails(@PathVariable Long id, Model model) {
        LitigationCase litigationCase = litigationCaseService.getLitigationCaseById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Litigation Case ID: " + id));
        model.addAttribute("litigationCase", litigationCase);
        return "case_pages/details-litigation-case";
    }

    @GetMapping("/details/{id}")
    public String getDetails(@PathVariable Long id, Model model) {
        var litigationCase = litigationCaseService.getLitigationCaseById(id)
                .orElseThrow(() -> new RuntimeException("Litigation case not found"));
        model.addAttribute("litigationCase", litigationCase);
        return "case_pages/details-litigation-case";
    }
}

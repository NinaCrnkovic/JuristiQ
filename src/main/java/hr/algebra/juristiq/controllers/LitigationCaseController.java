package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.enums.Court;
import hr.algebra.juristiq.enums.LitigationCaseType;
import hr.algebra.juristiq.models.Client;
import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.services.ClientService;
import hr.algebra.juristiq.services.LitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/JuristiQ/litigation-cases")
@AllArgsConstructor
public class LitigationCaseController {

    private final LitigationCaseService litigationCaseService;
    private final ClientService clientService;

    @GetMapping
    public String listLitigationCases(@RequestParam(value = "search", required = false) String search, Model model) {
        List<LitigationCase> litigationCases = litigationCaseService.getNonArchivedLitigationCases(search);
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
        model.addAttribute("caseTypes", LitigationCaseType.values());
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
        model.addAttribute("caseTypes", LitigationCaseType.values());
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
    @GetMapping("/archived")
    public String listArchivedLitigationCases(Model model) {
        List<LitigationCase> archivedCases = litigationCaseService.getArchivedLitigationCases();
        model.addAttribute("litigationCases", archivedCases);
        return "case_pages/arhiv-list-litigation-cases";
    }

    @GetMapping("/{id}/archive")
    public String archiveCase(@PathVariable Long id) {
        litigationCaseService.archiveCase(id, true);
        return "redirect:/JuristiQ/litigation-cases";
    }


    @GetMapping("/{id}/unarchive")
    public String unarchiveCase(@PathVariable Long id) {
        litigationCaseService.archiveCase(id, false);
        return "redirect:/JuristiQ/litigation-cases/archived";
    }

    @GetMapping("/my-cases")
    public String listMyLitigationCases(Principal principal, Model model) {
        if (principal != null) {
            String email = principal.getName(); // Get the logged-in lawyer's email
            List<LitigationCase> myLitigationCases = litigationCaseService.getCasesByLawyerEmail(email);
            model.addAttribute("litigationCases", myLitigationCases);
        } else {
            model.addAttribute("litigationCases", List.of());
        }
        return "case_pages/list-my-litigation-cases";
    }


}


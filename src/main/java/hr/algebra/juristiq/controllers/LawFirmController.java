package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.LawFirm;
import hr.algebra.juristiq.services.EmailService;
import hr.algebra.juristiq.services.LawFirmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/JuristiQ/lawfirms")
@AllArgsConstructor
public class LawFirmController {

    private final LawFirmService lawFirmService;
    private final EmailService emailService;

    @GetMapping
    public String getAllLawFirms(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<LawFirm> lawFirms = lawFirmService.searchLawFirms(keyword);
        model.addAttribute("lawFirms", lawFirms);
        model.addAttribute("keyword", keyword); // Popunjavanje inputa za pretraživanje
        return "lawfirm_pages/list-lawfirms";
    }

    @GetMapping("/add")
    public String showAddLawFirmForm(Model model) {
        model.addAttribute("lawFirm", new LawFirm());
        return "lawfirm_pages/add-lawfirm";
    }

    @PostMapping("/add")
    public String addLawFirm(@ModelAttribute LawFirm lawFirm) {
        lawFirmService.saveLawFirm(lawFirm);
        return "redirect:/JuristiQ/lawfirms";
    }

    @GetMapping("/edit/{id}")
    public String showEditLawFirmForm(@PathVariable Long id, Model model) {
        LawFirm lawFirm = lawFirmService.getLawFirmById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Law Firm ID: " + id));
        model.addAttribute("lawFirm", lawFirm);
        return "lawfirm_pages/edit-lawfirm";
    }

    @PostMapping("/edit/{id}")
    public String editLawFirm(@PathVariable Long id, @ModelAttribute LawFirm lawFirm) {
        lawFirm.setId(id);
        lawFirmService.saveLawFirm(lawFirm);
        return "redirect:/JuristiQ/lawfirms";
    }

    @GetMapping("/delete/{id}")
    public String deleteLawFirm(@PathVariable Long id) {
        lawFirmService.deleteLawFirm(id);
        return "redirect:/JuristiQ/lawfirms";
    }

    @GetMapping("/{id}/details")
    public String showLawFirmDetails(@PathVariable Long id, Model model) {
        LawFirm lawFirm = lawFirmService.getLawFirmById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Law Firm ID: " + id));
        model.addAttribute("lawFirm", lawFirm);
        return "lawfirm_pages/details-lawfirm";
    }



    @PostMapping("/auth/register-law-firm")
    public String registerLawFirm(@ModelAttribute LawFirm lawFirm, Model model) {
        // Spremi odvjetnički ured
        lawFirmService.saveLawFirm(lawFirm);

        // Generiraj registracijski kod
        String registrationCode = lawFirmService.generateRegistrationCode(lawFirm.getId());

        // Dodaj registracijski kod u model
        model.addAttribute("registrationCode", registrationCode);

        // Prikaži međustranicu s kodom
        return "auth_pages/registration-code";
    }


}

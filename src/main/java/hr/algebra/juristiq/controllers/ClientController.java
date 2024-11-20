package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.Client;
import hr.algebra.juristiq.services.ClientService;
import hr.algebra.juristiq.services.LawyerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/JuristiQ/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final LawyerService lawyerService; // Inject LawyerService to fetch the lawyer list

    @GetMapping
    public String getAllClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "client_pages/list-clients"; // Return the list of clients page
    }

    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("lawyers", lawyerService.getAllLawyers()); // Provide lawyers for the dropdown
        return "client_pages/add-client"; // Return the add client page
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/JuristiQ/clients"; // Redirect to the list of clients
    }

    @GetMapping("/edit/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID: " + id));
        model.addAttribute("client", client);
        model.addAttribute("lawyers", lawyerService.getAllLawyers()); // Provide lawyers for the dropdown
        return "client_pages/edit-client"; // Return the edit client page
    }

    @PostMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, @ModelAttribute Client client) {
        client.setId(id); // Ensure existing client is updated
        clientService.saveClient(client);
        return "redirect:/JuristiQ/clients"; // Redirect to the list of clients
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/JuristiQ/clients"; // Redirect to the list of clients
    }

    @GetMapping("/{id}/details")
    public String getClientDetails(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid client ID: " + id));
        model.addAttribute("client", client);
        return "client_pages/details-client";
    }

}

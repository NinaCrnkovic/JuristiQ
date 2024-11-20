package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.Client;

import hr.algebra.juristiq.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Client getClientByOib(String oib) {
        return clientRepository.findByOib(oib);
    }

    public Client saveClient(Client client) {
        if (clientRepository.existsByOib(client.getOib())) {
            throw new IllegalArgumentException("Client with the same OIB already exists.");
        }
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public List<Client> getClientsByIds(List<Long> plaintiffs) {
        return clientRepository.findAllById(plaintiffs);
    }

    public List<Client> searchClientsByName(String query) {
        return clientRepository.findByNameContainingIgnoreCase(query);
    }
}


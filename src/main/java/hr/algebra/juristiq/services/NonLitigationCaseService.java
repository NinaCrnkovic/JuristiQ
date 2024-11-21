package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.NonLitigationCase;
import hr.algebra.juristiq.repositories.NonLitigationCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NonLitigationCaseService {

    private final NonLitigationCaseRepository nonLitigationCaseRepository;

    public List<NonLitigationCase> searchNonLitigationCases(String searchTerm) {
        return nonLitigationCaseRepository.searchByKeyword(searchTerm);
    }

    public Optional<NonLitigationCase> getNonLitigationCaseById(Long id) {
        System.out.println("Fetching NonLitigationCase with ID: " + id);
        return nonLitigationCaseRepository.findById(id);
    }

    public void saveNonLitigationCase(NonLitigationCase nonLitigationCase) {
        if (nonLitigationCase.getId() != null) {
            // Update existing case
            if (!nonLitigationCaseRepository.existsById(nonLitigationCase.getId())) {
                throw new IllegalArgumentException("Non-litigation case with ID " + nonLitigationCase.getId() + " does not exist.");
            }
        }
        nonLitigationCaseRepository.save(nonLitigationCase);
    }

    public void deleteNonLitigationCase(Long id) {
        nonLitigationCaseRepository.deleteById(id);
    }

    public List<NonLitigationCase> getAllNonLitigationCases() {
        return nonLitigationCaseRepository.findAll();
    }

    public NonLitigationCase findById(Long id) {
        return nonLitigationCaseRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Non-Litigation Case ID: " + id));
    }
}

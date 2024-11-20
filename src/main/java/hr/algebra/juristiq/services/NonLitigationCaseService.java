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

    public List<NonLitigationCase> getAllNonLitigationCases() {
        return nonLitigationCaseRepository.findAll();
    }

    public Optional<NonLitigationCase> getNonLitigationCaseById(Long id) {
        return nonLitigationCaseRepository.findById(id);
    }

    public NonLitigationCase saveNonLitigationCase(NonLitigationCase nonLitigationCase) {
        return nonLitigationCaseRepository.save(nonLitigationCase);
    }

    public void deleteNonLitigationCase(Long id) {
        nonLitigationCaseRepository.deleteById(id);
    }
}

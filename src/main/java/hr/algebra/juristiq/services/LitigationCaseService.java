package hr.algebra.juristiq.services;



import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.repositories.LitigationCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LitigationCaseService {

    private final LitigationCaseRepository litigationCaseRepository;

    public List<LitigationCase> getAllLitigationCases() {
        return litigationCaseRepository.findAll();
    }

    public Optional<LitigationCase> getLitigationCaseById(Long id) {
        return litigationCaseRepository.findById(id);
    }

    public LitigationCase saveLitigationCase(LitigationCase litigationCase) {
        return litigationCaseRepository.save(litigationCase);
    }

    public void deleteLitigationCase(Long id) {
        litigationCaseRepository.deleteById(id);
    }
}

package hr.algebra.juristiq.services;



import hr.algebra.juristiq.models.NonLitigationAction;
import hr.algebra.juristiq.models.NonLitigationCase;
import hr.algebra.juristiq.repositories.NonLitigationActionRepository;
import hr.algebra.juristiq.repositories.NonLitigationCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NonLitigationActionService {

    private final NonLitigationActionRepository actionRepository;
    private final NonLitigationCaseService caseService;

    public Optional<NonLitigationAction> getActionById(Long id) {
        return actionRepository.findById(id);
    }

    public NonLitigationAction saveAction(NonLitigationAction action) {
        return actionRepository.save(action);
    }

    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }

    public void addActionToCase(Long caseId, NonLitigationAction action) {
        NonLitigationCase nonLitigationCase = caseService.getNonLitigationCaseById(caseId)
                .orElseThrow(() -> new RuntimeException("Non-litigation case not found"));

        action.setNonLitigationCase(nonLitigationCase);
        actionRepository.save(action);
    }
    public List<NonLitigationAction> getAllActions() {
        return actionRepository.findAll();
    }
}


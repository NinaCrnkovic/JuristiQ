package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.Action;
import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.repositories.ActionRepository;
import hr.algebra.juristiq.repositories.LitigationCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    private final LitigationCaseService litigationCaseService;



    public Optional<Action> getActionById(Long id) {
        return actionRepository.findById(id);
    }



    public Action saveAction(Action action) {
        return actionRepository.save(action);
    }

    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }



    public void addActionToCase(Long caseId, Action action) {
        LitigationCase litigationCase = litigationCaseService.getLitigationCaseById(caseId)
                .orElseThrow(() -> new RuntimeException("Litigation case not found"));

        action.setLitigationCase(litigationCase);
        actionRepository.save(action);
    }


    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }
}


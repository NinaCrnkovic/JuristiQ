package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.Action;
import hr.algebra.juristiq.repositories.ActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ActionService {

    private final ActionRepository actionRepository;

    public List<Action> getAllActions() {
        return actionRepository.findAll();
    }

    public Optional<Action> getActionById(Long id) {
        return actionRepository.findById(id);
    }

    public Action getActionByName(String name) {
        return actionRepository.findByName(name);
    }

    public Action saveAction(Action action) {
        return actionRepository.save(action);
    }

    public void deleteAction(Long id) {
        actionRepository.deleteById(id);
    }
}


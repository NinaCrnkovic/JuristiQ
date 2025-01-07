package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.models.NonLitigationCase;
import hr.algebra.juristiq.repositories.NonLitigationCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<NonLitigationCase> getNonArchivedNonLitigationCases() {
        return nonLitigationCaseRepository.findByIsArchivedFalse();
    }

    public List<NonLitigationCase> getArchivedNonLitigationCases() {
        return nonLitigationCaseRepository.findByIsArchivedTrue();
    }

    public void archiveCase(Long id, boolean archive) {
        NonLitigationCase nonLitigationCase = nonLitigationCaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Non-litigation case with ID " + id + " does not exist."));
        nonLitigationCase.setArchived(archive);
        nonLitigationCaseRepository.save(nonLitigationCase);
    }

    public List<NonLitigationCase> searchNonArchivedNonLitigationCases(String searchTerm) {
        return nonLitigationCaseRepository.searchByKeywordAndIsArchivedFalse(searchTerm);
    }

    public List<NonLitigationCase> getCasesByLawyerEmail(String email) {
        return nonLitigationCaseRepository.findAll().stream()
                .filter(nonlitigationCase -> nonlitigationCase.getRepresentedParties().stream()
                        .anyMatch(party -> party.getLawyer() != null && email.equals(party.getLawyer().getEmail())))
                .collect(Collectors.toList());
    }



}

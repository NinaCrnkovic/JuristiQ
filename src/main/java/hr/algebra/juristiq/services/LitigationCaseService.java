package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.LitigationCase;
import hr.algebra.juristiq.repositories.LitigationCaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LitigationCaseService {

    private final LitigationCaseRepository litigationCaseRepository;



    public List<LitigationCase> searchLitigationCases(String searchTerm) {
        return litigationCaseRepository.searchByKeyword(searchTerm);
    }

    public Optional<LitigationCase> getLitigationCaseById(Long id) {
        System.out.println("Fetching LitigationCase with ID: " + id);
        return litigationCaseRepository.findById(id);
    }


    public void saveLitigationCase(LitigationCase litigationCase) {
        if (litigationCase.getId() != null) {
            // Update existing case
            if (!litigationCaseRepository.existsById(litigationCase.getId())) {
                throw new IllegalArgumentException("Litigation case with ID " + litigationCase.getId() + " does not exist.");
            }
        }
        litigationCaseRepository.save(litigationCase);
    }

    public void deleteLitigationCase(Long id) {
        litigationCaseRepository.deleteById(id);
    }

    public List<LitigationCase> getAllLitigationCases() {
        return litigationCaseRepository.findAll();
    }

    public List<LitigationCase> getNonArchivedLitigationCases(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return litigationCaseRepository.findByIsArchivedFalse();
        } else {
            return litigationCaseRepository.searchByKeywordAndIsArchivedFalse(searchTerm);
        }
    }

    public List<LitigationCase> getArchivedLitigationCases() {
        return litigationCaseRepository.findByIsArchivedTrue();
    }

    public void archiveCase(Long id, boolean archive) {
        LitigationCase litigationCase = litigationCaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Litigation case with ID " + id + " does not exist."));
        litigationCase.setArchived(archive);
        litigationCaseRepository.save(litigationCase);
    }

    public List<LitigationCase> getCasesByLawyerEmail(String email) {
        return litigationCaseRepository.findAll().stream()
                .filter(litigationCase -> litigationCase.getRepresentedParties().stream()
                        .anyMatch(party -> party.getLawyer() != null && email.equals(party.getLawyer().getEmail())))
                .collect(Collectors.toList());
    }



}

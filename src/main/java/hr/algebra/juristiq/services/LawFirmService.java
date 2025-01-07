package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.LawFirm;
import hr.algebra.juristiq.repositories.LawFirmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LawFirmService {

    private final LawFirmRepository lawFirmRepository;

    public List<LawFirm> getAllLawFirms() {
        return lawFirmRepository.findAll();
    }

    public Optional<LawFirm> getLawFirmById(Long id) {
        return lawFirmRepository.findById(id);
    }

    public LawFirm getLawFirmByOib(String oib) {
        return lawFirmRepository.findByOib(oib);
    }

    public LawFirm saveLawFirm(LawFirm lawFirm) {
        return lawFirmRepository.save(lawFirm);
    }

    public void deleteLawFirm(Long id) {
        lawFirmRepository.deleteById(id);
    }

    public List<LawFirm> searchLawFirms(String keyword) {
        return (keyword == null || keyword.isEmpty()) ? lawFirmRepository.findAll() : lawFirmRepository.searchByKeyword(keyword);
    }

    public String generateRegistrationCode(Long lawFirmId) {
        LawFirm lawFirm = lawFirmRepository.findById(lawFirmId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Law Firm ID"));
        String registrationCode = UUID.randomUUID().toString();
        lawFirm.setRegistrationCode(registrationCode);
        lawFirmRepository.save(lawFirm);
        return registrationCode;
    }
    public Optional<LawFirm> findByRegistrationCode(String registrationCode) {
        return lawFirmRepository.findByRegistrationCode(registrationCode);
    }


}


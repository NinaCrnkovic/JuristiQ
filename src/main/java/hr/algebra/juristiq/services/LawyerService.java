package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.Lawyer;
import hr.algebra.juristiq.repositories.LawyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LawyerService {

    private final LawyerRepository lawyerRepository;

    public List<Lawyer> getAllLawyers() {
        return lawyerRepository.findAll();
    }

    public Optional<Lawyer> getLawyerById(Long id) {
        return lawyerRepository.findById(id);
    }

    public Lawyer saveLawyer(Lawyer lawyer) {
        return lawyerRepository.save(lawyer);
    }

    public void deleteLawyer(Long id) {
        lawyerRepository.deleteById(id);
    }

    public List<Lawyer> searchLawyers(String searchTerm) {
        return lawyerRepository.searchByAllProperties(searchTerm);
    }
}



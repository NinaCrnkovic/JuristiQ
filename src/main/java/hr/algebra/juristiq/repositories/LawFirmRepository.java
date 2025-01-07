package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.LawFirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LawFirmRepository extends JpaRepository<LawFirm, Long> {
    LawFirm findByOib(String oib);

    @Query("SELECT l FROM LawFirm l WHERE " +
            "LOWER(l.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.oib) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(l.phone) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<LawFirm> searchByKeyword(@Param("keyword") String keyword);

    Optional<LawFirm> findByRegistrationCode(String registrationCode);

}

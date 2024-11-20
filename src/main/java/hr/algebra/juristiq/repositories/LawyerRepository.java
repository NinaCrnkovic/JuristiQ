package hr.algebra.juristiq.repositories;



import hr.algebra.juristiq.models.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    // Primjeri custom upita
    Lawyer findByOib(String oib);


    @Query("SELECT l FROM Lawyer l WHERE " +
            "LOWER(l.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(l.surname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(l.oib) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(l.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(l.phone) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Lawyer> searchByAllProperties(@Param("searchTerm") String searchTerm);
}

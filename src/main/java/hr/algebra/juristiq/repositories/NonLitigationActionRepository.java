package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.Action;
import hr.algebra.juristiq.models.NonLitigationAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonLitigationActionRepository extends JpaRepository<NonLitigationAction, Long> {
    // Primjeri custom upita
    NonLitigationAction findByType(String type);

}

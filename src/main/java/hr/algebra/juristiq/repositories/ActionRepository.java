package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    // Primjeri custom upita
    Action findByName(String name);
}
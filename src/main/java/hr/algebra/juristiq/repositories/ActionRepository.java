package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {
    // Primjeri custom upita
    Action findByType(String type);
    @Query("SELECT a FROM Action a WHERE a.lawyer.email = :email")
    List<Action> findByLawyerEmail(@Param("email") String email);



}
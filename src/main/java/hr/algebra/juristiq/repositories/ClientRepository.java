package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Primjeri custom upita
    Client findByOib(String oib);
    boolean existsByOib(String oib);

    List<Client> findAllById(Iterable<Long> ids);

    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(c.surname) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Client> findByNameContainingIgnoreCase(@Param("query") String query);
}


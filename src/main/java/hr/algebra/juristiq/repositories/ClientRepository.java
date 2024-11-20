package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Primjeri custom upita
    Client findByOib(String oib);
    boolean existsByOib(String oib);
}

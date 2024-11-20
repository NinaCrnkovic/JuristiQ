package hr.algebra.juristiq.repositories;
import hr.algebra.juristiq.models.NonLitigationCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NonLitigationCaseRepository extends JpaRepository<NonLitigationCase, Long> {

}

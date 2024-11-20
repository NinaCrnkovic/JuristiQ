package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.LitigationCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LitigationCaseRepository extends JpaRepository<LitigationCase, Long> {
    // Custom upiti za LitigationCase

}

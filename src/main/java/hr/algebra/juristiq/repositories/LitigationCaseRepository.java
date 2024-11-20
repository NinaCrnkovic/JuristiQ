package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.LitigationCase;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LitigationCaseRepository extends JpaRepository<LitigationCase, Long> {
    @Query("SELECT lc FROM LitigationCase lc WHERE " +
            "LOWER(lc.designation) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(lc.judge) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR lc.court LIKE CONCAT('%', :searchTerm, '%')")
    List<LitigationCase> searchByKeyword(@Param("searchTerm") String searchTerm);
}

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
    List<LitigationCase> findByIsArchivedFalse();

    List<LitigationCase> findByIsArchivedTrue();

    @Query("SELECT lc FROM LitigationCase lc WHERE lc.isArchived = false AND " +
            "(lc.designation LIKE %:keyword% OR lc.judge LIKE %:keyword%)")
    List<LitigationCase> searchByKeywordAndIsArchivedFalse(String keyword);

    @Query("SELECT lc FROM LitigationCase lc WHERE lc.designation LIKE %:keyword% OR lc.judge LIKE %:keyword%")
    List<LitigationCase> searchByKeyword(String keyword);

}

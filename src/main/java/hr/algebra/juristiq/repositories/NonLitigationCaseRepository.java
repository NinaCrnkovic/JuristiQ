package hr.algebra.juristiq.repositories;


import hr.algebra.juristiq.models.NonLitigationCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NonLitigationCaseRepository extends JpaRepository<NonLitigationCase, Long> {

    @Query("SELECT nlc FROM NonLitigationCase nlc WHERE " +
            "LOWER(nlc.internalReferenceNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(nlc.caseType) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<NonLitigationCase> searchByKeyword(@Param("searchTerm") String searchTerm);
    @Query("SELECT nlc FROM NonLitigationCase nlc WHERE nlc.isArchived = false AND " +
            "(LOWER(nlc.internalReferenceNumber) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(nlc.caseType) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<NonLitigationCase> searchByKeywordAndIsArchivedFalse(@Param("searchTerm") String searchTerm);


    List<NonLitigationCase> findByIsArchivedFalse();

    List<NonLitigationCase> findByIsArchivedTrue();

}
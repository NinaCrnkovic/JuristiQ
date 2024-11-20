package hr.algebra.juristiq.repositories;

import hr.algebra.juristiq.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByLitigationCaseId(Long litigationCaseId);
}


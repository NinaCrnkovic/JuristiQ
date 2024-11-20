package hr.algebra.juristiq.services;

import hr.algebra.juristiq.models.Document;
import hr.algebra.juristiq.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<Document> getDocumentsByCaseId(Long caseId) {
        return documentRepository.findByLitigationCaseId(caseId);
    }

    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElse(null);
    }
}


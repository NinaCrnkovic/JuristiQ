package hr.algebra.juristiq.controllers;

import hr.algebra.juristiq.models.Document;
import hr.algebra.juristiq.services.DocumentService;
import hr.algebra.juristiq.services.LitigationCaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/JuristiQ/documents")
@AllArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final LitigationCaseService litigationCaseService;

    @GetMapping("/add")
    public String showAddDocumentsPage(@RequestParam Long caseId, Model model) {
        model.addAttribute("caseId", caseId);
        return "documents_page/add-documents"; // Ensure this matches your HTML path
    }

    @PostMapping("/add")
    public String addDocument(@RequestParam("caseId") Long caseId, @RequestParam("file") MultipartFile file) {
        try {
            var litigationCase = litigationCaseService.getLitigationCaseById(caseId)
                    .orElseThrow(() -> new RuntimeException("Case not found"));
            Document document = new Document();
            document.setFileName(file.getOriginalFilename());
            document.setFileType(file.getContentType());
            document.setData(file.getBytes());
            document.setLitigationCase(litigationCase);
            documentService.saveDocument(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/JuristiQ/litigation-cases/details/" + caseId;
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        Document document = documentService.getDocumentById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .contentType(MediaType.parseMediaType(document.getFileType()))
                .body(document.getData());
    }
}


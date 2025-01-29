package fr.efrei.rag.web.rest;

import fr.efrei.rag.domain.Document;
import fr.efrei.rag.service.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/documents")
public class DocumentResource {

    private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);
    private final DocumentService documentService;

    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<Document> createDocument(@RequestBody Document document) throws URISyntaxException {
        log.debug("REST request to save Document : {}", document);
        Document result = documentService.buildAndSave(document);
        return ResponseEntity.created(new URI("/documents/" + result.getId())).body(result);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getDocuments() {
        log.debug("REST request to get all Documents");
        List<Document> documents = documentService.findAll();
        return ResponseEntity.ok(documents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id) {
        log.debug("REST request to get Document : {}", id);
        Document document = documentService.findById(id);
        return ResponseEntity.ok(document);
    }

    @PostMapping("/documents/chat/{user}")
    public String chat(@RequestBody String query) throws InterruptedException {
        String result = documentService.chat(query);

        return result;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        log.debug("REST request to delete Document : {}", id);
        documentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

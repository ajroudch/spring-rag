package fr.efrei.rag.service;

import fr.efrei.rag.domain.Document;
import fr.efrei.rag.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DocumentService {
    private static final Logger log = LoggerFactory.getLogger(DocumentService.class);
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document buildAndSaveDocument(Document document) {
        log.debug("Request to save Document : {}", document);
        return documentRepository.save(document);
    }

    public Optional<Document> findById(Long id) {
        return documentRepository.findById(id);
    }
}

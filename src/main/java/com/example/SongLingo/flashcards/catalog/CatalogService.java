package com.example.SongLingo.flashcards.catalog;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.flashcards.flashCard.FlashCardRepository;
import com.example.SongLingo.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private final FlashCardRepository flashCardRepository;
    private final EntityMapper entityMapper;

    public CatalogService(CatalogRepository catalogRepository, FlashCardRepository flashCardRepository,
                          EntityMapper entityMapper) {
        this.catalogRepository = catalogRepository;
        this.flashCardRepository = flashCardRepository;
        this.entityMapper = entityMapper;
    }

    public CatalogDTO createCatalog(CatalogRequest catalogRequest) {
        if (catalogRepository.existsByNameIgnoreCase(catalogRequest.getName())) {
            throw new ExistsException("Catalog with this name already exists");
        }
        Catalog catalog = new Catalog();
        catalog.setName(catalogRequest.getName());

        catalogRepository.save(catalog);

        return entityMapper.mapCatalogToCatalogDTO(catalog);
    }

    public void deleteCatalogById(Long catalogId) {
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow(() ->
                new EntityNotFoundException("Catalog with ID " + catalogId + " not found"));

        if (flashCardRepository.existsByCatalogId(catalogId)) {
            throw new IllegalStateException("Cannot delete catalog as it is associated with existing flash cards.");
        }
        catalogRepository.delete(catalog);
    }

    public void deleteAllCatalogs() {
        if (flashCardRepository.findFirstBy().isPresent()) {
            throw new IllegalStateException("Cannot delete catalogs as they are associated with existing flash cards.");
        }
        catalogRepository.deleteAll();
    }
}

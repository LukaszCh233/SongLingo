package com.example.SongLingo.flashcards.catalog;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.mapper.EntityMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogService {
    private final CatalogRepository catalogRepository;
    private final EntityMapper entityMapper;

    public CatalogService(CatalogRepository catalogRepository, EntityMapper entityMapper) {
        this.catalogRepository = catalogRepository;
        this.entityMapper = entityMapper;
    }

    public CatalogDTO createCatalog(String name) {
        if (catalogRepository.existsByNameIgnoreCase(name)) {
            throw new ExistsException("Catalog with this name already exists");
        }
        Catalog catalog = new Catalog();
        catalog.setName(name);

        catalogRepository.save(catalog);

        return entityMapper.mapCatalogToCatalogDTO(catalog);
    }

    public void deleteCatalogById(Long catalogId) {
        Optional<Catalog> catalog = catalogRepository.findById(catalogId);
        catalog.ifPresent(catalogRepository::delete);
    }

    public void deleteAllCatalogs() {
        catalogRepository.deleteAll();
    }
}

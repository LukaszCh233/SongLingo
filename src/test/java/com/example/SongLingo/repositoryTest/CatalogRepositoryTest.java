package com.example.SongLingo.repositoryTest;

import com.example.SongLingo.flashcards.entity.Catalog;
import com.example.SongLingo.flashcards.repository.CatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class CatalogRepositoryTest {
    @Autowired
    CatalogRepository catalogRepository;

    @BeforeEach
    public void SetUp() {
        catalogRepository.deleteAll();
    }

    @Test
    public void catalogExistsByNameIgnoreCase() {
        newCatalog("catalog");

        assertTrue(catalogRepository.existsByNameIgnoreCase("CATALOG"));
    }

    private void newCatalog(String name) {
        Catalog catalog = new Catalog();
        catalog.setName(name);

        catalogRepository.save(catalog);
    }
}

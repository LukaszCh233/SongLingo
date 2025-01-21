package com.example.SongLingo.flashcards.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    boolean existsByNameIgnoreCase(String name);
}

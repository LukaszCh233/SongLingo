package com.example.SongLingo.flashcards.repository;

import com.example.SongLingo.flashcards.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    boolean existsByNameIgnoreCase(String name);
}

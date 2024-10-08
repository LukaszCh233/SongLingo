package com.example.SongLingo.song.repository;

import com.example.SongLingo.song.entity.SongCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongCategoryRepository extends JpaRepository<SongCategory, Long> {
    Optional<SongCategory> findByNameIgnoreCase(String name);
}

package com.example.SongLingo.song.songCategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongCategoryRepository extends JpaRepository<SongCategory, Long> {
    Optional<SongCategory> findByNameIgnoreCase(String name);
}

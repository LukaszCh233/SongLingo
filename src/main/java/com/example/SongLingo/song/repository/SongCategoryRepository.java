package com.example.SongLingo.song.repository;

import com.example.SongLingo.song.entity.SongCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SongCategoryRepository extends JpaRepository<SongCategory, Long> {
    @Query("SELECT s FROM SongCategory s WHERE LOWER(s.name) = LOWER(:name)")
    Optional<SongCategory> findByNameIgnoreCase(@Param("name") String name);
}

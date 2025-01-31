package com.example.SongLingo.song.song;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findBySongCategoryNameIgnoreCase(String name);

    boolean existsBySongCategoryId(Long songCategoryId);

    List<Song> findByTitleIgnoreCase(String title);

    List<Song> findByAuthorIgnoreCase(String author);

    Optional<Song> findFirstBy();
}

package com.example.SongLingo.song.song;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findBySongCategoryNameIgnoreCase(String name);

    List<Song> findByTitleIgnoreCase(String title);

    List<Song> findByAuthorIgnoreCase(String author);
}

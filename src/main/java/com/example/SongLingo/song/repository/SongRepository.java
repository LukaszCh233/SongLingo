package com.example.SongLingo.song.repository;

import com.example.SongLingo.song.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
}

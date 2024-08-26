package com.example.SongLingo.song.repository;

import com.example.SongLingo.song.entity.SongText;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongTextRepository extends JpaRepository<SongText, Long> {
}

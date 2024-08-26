package com.example.SongLingo.song.controller;

import com.example.SongLingo.song.dto.SongDTO;
import com.example.SongLingo.song.entity.Song;
import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.entity.SongText;
import com.example.SongLingo.song.service.SongCategoryService;
import com.example.SongLingo.song.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class SongAdminController {
    private final SongCategoryService songCategoryService;
    private final SongService songService;

    public SongAdminController(SongCategoryService songCategoryService, SongService songService) {
        this.songCategoryService = songCategoryService;
        this.songService = songService;
    }

    @PostMapping("/song-category")
    public ResponseEntity<SongCategory> addSongCategory(@RequestBody SongCategory songCategory) {
        SongCategory createSongCategory = songCategoryService.createSongCategory(songCategory);

        return ResponseEntity.ok(createSongCategory);
    }

    @DeleteMapping("/song-category/{id}")
    public ResponseEntity<String> deleteSongCategory(@PathVariable Long id) {
        songCategoryService.deleteSongCategoryById(id);

        return ResponseEntity.ok("Category has been deleted");
    }

    @DeleteMapping("/song-categories")
    public ResponseEntity<String> deleteAllSongCategories() {
        songCategoryService.deleteAllSongCategories();
        return ResponseEntity.ok("All categories has been deleted");
    }

    @PutMapping("/song-category/{id}")
    public ResponseEntity<String> updateSongCategory(@PathVariable Long id, @RequestBody SongCategory songCategory) {
        songCategoryService.updateSongCategory(id, songCategory);

        return ResponseEntity.ok("Song category has been updated");
    }

    @PostMapping("/song")
    public ResponseEntity<SongDTO> addSong(@RequestBody Song song) {
        SongDTO createSong = songService.createSong(song.getTitle(), song.getAuthor(), song.getSongCategory().getId());

        return ResponseEntity.ok(createSong);
    }

    @PostMapping("/song/{id}/text")
    public ResponseEntity<String> addTextToSong(@PathVariable Long id, @RequestBody SongText songText) {
        songService.addTextToSong(id, songText);

        return ResponseEntity.ok("Text has been added to song");
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        songService.deleteSongById(id);

        return ResponseEntity.ok("Song has been deleted");
    }

    @DeleteMapping("/songs")
    public ResponseEntity<String> deleteAllSongs() {
        songService.deleteAllSongs();

        return ResponseEntity.ok("All songs has been deleted");
    }
}

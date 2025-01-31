package com.example.SongLingo.song.controller;

import com.example.SongLingo.song.SongText.SongTextRequest;
import com.example.SongLingo.song.SongText.SongTextService;
import com.example.SongLingo.song.song.SongCreationRequest;
import com.example.SongLingo.song.song.SongDTO;
import com.example.SongLingo.song.song.SongService;
import com.example.SongLingo.song.songCategory.SongCategory;
import com.example.SongLingo.song.songCategory.SongCategoryRequest;
import com.example.SongLingo.song.songCategory.SongCategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class SongAdminController {
    private final SongCategoryService songCategoryService;
    private final SongService songService;
    private final SongTextService songTextService;

    public SongAdminController(SongCategoryService songCategoryService, SongService songService,
                               SongTextService songTextService) {
        this.songCategoryService = songCategoryService;
        this.songService = songService;
        this.songTextService = songTextService;
    }

    @PostMapping("/song-category")
    public ResponseEntity<SongCategory> addSongCategory(@RequestBody @Valid SongCategoryRequest songCategoryRequest) {
        SongCategory createSongCategory = songCategoryService.createSongCategory(songCategoryRequest);

        return ResponseEntity.ok(createSongCategory);
    }

    @DeleteMapping("/song-category/{songCategoryId}")
    public ResponseEntity<String> deleteSongCategory(@PathVariable Long songCategoryId) {
        songCategoryService.deleteSongCategoryById(songCategoryId);

        return ResponseEntity.ok("Category has been deleted");
    }

    @DeleteMapping("/song-categories")
    public ResponseEntity<String> deleteAllSongCategories() {
        songCategoryService.deleteAllSongCategories();
        return ResponseEntity.ok("All categories has been deleted");
    }

    @PutMapping("/song-category/{songCategoryId}")
    public ResponseEntity<String> updateSongCategory(@PathVariable Long songCategoryId,
                                                     @RequestBody @Valid SongCategoryRequest songCategoryRequest) {
        songCategoryService.updateSongCategory(songCategoryId, songCategoryRequest);

        return ResponseEntity.ok("Song category has been updated");
    }

    @PostMapping("/song")
    public ResponseEntity<SongDTO> addSong(@RequestBody @Valid SongCreationRequest songCreationRequest) {
        SongDTO createSong = songService.createSong(songCreationRequest);

        return ResponseEntity.ok(createSong);
    }

    @PostMapping("/song/{songId}/text")
    public ResponseEntity<String> addTextToSong(@PathVariable Long songId,
                                                @RequestBody @Valid SongTextRequest songText) {
        songTextService.createSongText(songId, songText);

        return ResponseEntity.ok("Text has been added to song");
    }

    @DeleteMapping("/song/{songId}")
    public ResponseEntity<String> deleteSong(@PathVariable Long songId) {
        songService.deleteSongById(songId);

        return ResponseEntity.ok("Song has been deleted");
    }

    @DeleteMapping("/songs")
    public ResponseEntity<String> deleteAllSongs() {
        songService.deleteAllSongs();

        return ResponseEntity.ok("All songs has been deleted");
    }

    @PutMapping("/song/{songId}")
    public ResponseEntity<String> updateSong(@PathVariable Long idSong,
                                             @RequestBody @Valid SongCreationRequest songCreationRequest) {
        songService.updateSong(idSong, songCreationRequest);

        return ResponseEntity.ok("Song has been updated");
    }

    @PutMapping("songText/{songId}")
    public ResponseEntity<String> updateSongText(@PathVariable Long songId,
                                                 @RequestBody @Valid SongTextRequest songTextRequest) {
        songTextService.createSongText(songId, songTextRequest);

        return ResponseEntity.ok("Song text has been updated");
    }
}

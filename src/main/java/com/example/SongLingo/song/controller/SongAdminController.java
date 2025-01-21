package com.example.SongLingo.song.controller;

import com.example.SongLingo.song.SongText.SongTextRequest;
import com.example.SongLingo.song.SongText.SongTextService;
import com.example.SongLingo.song.song.SongCreationRequest;
import com.example.SongLingo.song.song.SongDTO;
import com.example.SongLingo.song.song.SongService;
import com.example.SongLingo.song.songCategory.SongCategory;
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

    @PostMapping("/song-category/{categoryName}")
    public ResponseEntity<SongCategory> addSongCategory(@PathVariable String categoryName) {
        SongCategory createSongCategory = songCategoryService.createSongCategory(categoryName);

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

    @PutMapping("/song-category/{songCategoryId}/{name}")
    public ResponseEntity<String> updateSongCategory(@PathVariable Long songCategoryId,
                                                     @PathVariable String name) {
        songCategoryService.updateSongCategory(songCategoryId, name);

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
    public ResponseEntity<SongDTO> updateSong(@PathVariable Long idSong,
                                              @RequestBody @Valid SongCreationRequest songCreationRequest) {
        SongDTO updatedSong = songService.updateSong(idSong, songCreationRequest);

        return ResponseEntity.ok(updatedSong);
    }

    @PutMapping("songText/{songId}")
    public ResponseEntity<String> updateSongText(@PathVariable Long songId,
                                                 @RequestBody @Valid SongTextRequest songText) {
        songTextService.createSongText(songId, songText);

        return ResponseEntity.ok("Song text has been updated");
    }
}

package com.example.SongLingo.song.controller;

import com.example.SongLingo.song.SongText.SongTextDTO;
import com.example.SongLingo.song.SongText.SongTextService;
import com.example.SongLingo.song.song.SongDTO;
import com.example.SongLingo.song.song.SongService;
import com.example.SongLingo.song.songCategory.SongCategoryDTO;
import com.example.SongLingo.song.songCategory.SongCategoryRequest;
import com.example.SongLingo.song.songCategory.SongCategoryService;
import com.example.SongLingo.translate.TranslationService;
import com.example.SongLingo.translate.WordTranslation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/common")
public class SongCommonController {
    private final SongCategoryService songCategoryService;
    private final SongService songService;
    private final SongTextService songTextService;
    private final TranslationService translationService;

    public SongCommonController(SongCategoryService songCategoryService, SongService songService,
                                SongTextService songTextService, TranslationService translationService) {
        this.songCategoryService = songCategoryService;
        this.songService = songService;
        this.songTextService = songTextService;
        this.translationService = translationService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<SongCategoryDTO>> displayAllCategories() {
        List<SongCategoryDTO> categoryList = songCategoryService.findAllSongsCategory();

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongDTO>> displaySongList() {
        List<SongDTO> songList = songService.findAllSongs();

        return ResponseEntity.ok(songList);
    }

    @GetMapping("songs/category")
    public ResponseEntity<List<SongDTO>> displaySongsByCategory(
            @RequestBody @Valid SongCategoryRequest songCategoryRequest) {
        List<SongDTO> songsOfCategory = songService.findSongsByCategoryName(songCategoryRequest);

        return ResponseEntity.ok(songsOfCategory);
    }

    @GetMapping("songs/title/{title}")
    public ResponseEntity<List<SongDTO>> displaySongsByTitle(@PathVariable String title) {
        List<SongDTO> songsOfCategory = songService.findSongsByTitle(title);

        return ResponseEntity.ok(songsOfCategory);
    }

    @GetMapping("songs/author/{author}")
    public ResponseEntity<List<SongDTO>> displaySongsByAuthor(@PathVariable String author) {
        List<SongDTO> songsOfCategory = songService.findSongsByAuthor(author);

        return ResponseEntity.ok(songsOfCategory);
    }

    @GetMapping("/song/{id}/text")
    public ResponseEntity<SongTextDTO> displaySongText(@PathVariable Long id) {
        SongTextDTO songText = songTextService.findSongTextBySongId(id);

        return ResponseEntity.ok(songText);
    }

    @PostMapping("/song/{id}/text/{language}/translate")
    public ResponseEntity<String> translateSongText(@PathVariable Long id, @PathVariable String language) {
        String translatedText = translationService.translateSongText(id, language);

        return ResponseEntity.ok(translatedText);
    }

    @PostMapping("/translate-word")
    public ResponseEntity<String> translateWord(@RequestBody @Valid WordTranslation wordTranslation) {
        String translatedWord = translationService.translateWord(wordTranslation);
        return ResponseEntity.ok(translatedWord);
    }
}

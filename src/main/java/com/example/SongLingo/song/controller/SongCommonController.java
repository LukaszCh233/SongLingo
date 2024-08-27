package com.example.SongLingo.song.controller;

import com.example.SongLingo.song.dto.SongCategoryDTO;
import com.example.SongLingo.song.dto.SongDTO;
import com.example.SongLingo.song.dto.SongTextDTO;
import com.example.SongLingo.song.service.SongCategoryService;
import com.example.SongLingo.song.service.SongService;
import com.example.SongLingo.translate.WordTranslation;
import com.example.SongLingo.translate.TranslationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/common")
public class SongCommonController {
    private final SongCategoryService songCategoryService;
    private final SongService songService;
    private final TranslationService translationService;

    public SongCommonController(SongCategoryService songCategoryService, SongService songService,
                                TranslationService translationService) {
        this.songCategoryService = songCategoryService;
        this.songService = songService;
        this.translationService = translationService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<SongCategoryDTO>> displayAllCategories() {
        List<SongCategoryDTO> categoryList = songCategoryService.findAllSongCategory();

        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/songs")
    public ResponseEntity<List<SongDTO>> displaySongList() {
        List<SongDTO> songList = songService.findAllSongs();

        return ResponseEntity.ok(songList);
    }

    @GetMapping("/song/{id}/text")
    public ResponseEntity<SongTextDTO> displaySongText(@PathVariable Long id) {
        SongTextDTO songText = songService.findSongTextBySongId(id);

        return ResponseEntity.ok(songText);
    }

    @PostMapping("/song/{id}/text/{language}/translate")
    public ResponseEntity<String> translateSongText(@PathVariable Long id, @PathVariable String language) {
        String translatedText = translationService.translateSongText(id, language);

        return ResponseEntity.ok(translatedText);
    }

    @PostMapping("/translate-word")
    public ResponseEntity<String> translateWord(@RequestBody WordTranslation wordTranslation) {
        String translatedWord = translationService.translateWord(wordTranslation);
        return ResponseEntity.ok(translatedWord);
    }
}

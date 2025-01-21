package com.example.SongLingo.flashcards.controller;

import com.example.SongLingo.flashcards.catalog.CatalogDTO;
import com.example.SongLingo.flashcards.catalog.CatalogService;
import com.example.SongLingo.flashcards.flashCard.FlashCardDTO;
import com.example.SongLingo.flashcards.flashCard.FlashCardService;
import com.example.SongLingo.translate.WordTranslation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/common")
public class FlashCardController {
    private final FlashCardService flashCardService;
    private final CatalogService catalogService;

    public FlashCardController(FlashCardService flashCardService, CatalogService catalogService) {
        this.flashCardService = flashCardService;
        this.catalogService = catalogService;
    }

    @PostMapping("/catalog/{name}")
    public ResponseEntity<CatalogDTO> addCatalog(@PathVariable String name) {
        CatalogDTO catalog = catalogService.createCatalog(name);

        return ResponseEntity.ok(catalog);
    }

    @PostMapping("/flashCard/{id}")
    public ResponseEntity<FlashCardDTO> addFlashCard(@PathVariable Long id,
                                                     @RequestBody @Valid WordTranslation wordTranslation) {
        FlashCardDTO flashCard = flashCardService.createFlashCard(id, wordTranslation);

        return ResponseEntity.ok(flashCard);
    }

    @GetMapping("/flashCard-set/{catalogId}")
    public ResponseEntity<List<FlashCardDTO>> displayFlashCards(@PathVariable Long catalogId) {
        List<FlashCardDTO> flashCardList = flashCardService.findFlashCardsByCatalogId(catalogId);

        return ResponseEntity.ok(flashCardList);
    }

    @DeleteMapping("/catalog/{id}")
    public ResponseEntity<String> deleteCatalog(@PathVariable Long id) {
        catalogService.deleteCatalogById(id);

        return ResponseEntity.ok("Catalog has been deleted");
    }

    @DeleteMapping("/catalogs")
    public ResponseEntity<?> deleteAllCatalogs() {
        catalogService.deleteAllCatalogs();

        return ResponseEntity.ok("Catalogs has been deleted");
    }
}

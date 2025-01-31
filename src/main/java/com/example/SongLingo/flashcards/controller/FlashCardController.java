package com.example.SongLingo.flashcards.controller;

import com.example.SongLingo.flashcards.catalog.CatalogDTO;
import com.example.SongLingo.flashcards.catalog.CatalogRequest;
import com.example.SongLingo.flashcards.catalog.CatalogService;
import com.example.SongLingo.flashcards.flashCard.FlashCardDTO;
import com.example.SongLingo.flashcards.flashCard.FlashCardRequest;
import com.example.SongLingo.flashcards.flashCard.FlashCardService;
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

    @PostMapping("/catalog")
    public ResponseEntity<CatalogDTO> addCatalog(@RequestBody @Valid CatalogRequest catalogRequest) {
        CatalogDTO catalog = catalogService.createCatalog(catalogRequest);

        return ResponseEntity.ok(catalog);
    }

    @PostMapping("/flashCard")
    public ResponseEntity<FlashCardDTO> addFlashCard(@RequestBody @Valid FlashCardRequest flashCardRequest) {
        FlashCardDTO flashCard = flashCardService.createFlashCard(flashCardRequest);

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
    public ResponseEntity<String> deleteAllCatalogs() {
        catalogService.deleteAllCatalogs();

        return ResponseEntity.ok("Catalogs has been deleted");
    }
}

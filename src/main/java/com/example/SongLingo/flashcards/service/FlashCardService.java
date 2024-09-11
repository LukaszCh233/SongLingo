package com.example.SongLingo.flashcards.service;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.flashcards.dto.CatalogDTO;
import com.example.SongLingo.flashcards.dto.FlashCardDTO;
import com.example.SongLingo.flashcards.entity.Catalog;
import com.example.SongLingo.flashcards.entity.FlashCard;
import com.example.SongLingo.flashcards.repository.CatalogRepository;
import com.example.SongLingo.flashcards.repository.FlashCardRepository;
import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.translate.TranslationService;
import com.example.SongLingo.translate.WordTranslation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {
    private final FlashCardRepository flashCardRepository;
    private final CatalogRepository catalogRepository;
    private final TranslationService translationService;
    private final EntityMapper entityMapper;

    public FlashCardService(FlashCardRepository flashCardRepository, CatalogRepository catalogRepository,
                            TranslationService translationService, EntityMapper entityMapper) {
        this.flashCardRepository = flashCardRepository;
        this.catalogRepository = catalogRepository;
        this.translationService = translationService;
        this.entityMapper = entityMapper;
    }

    public CatalogDTO createCatalog(String name) {
        if (catalogRepository.existsByNameIgnoreCase(name)) {
            throw new ExistsException("Catalog with this name already exists");
        }
        Catalog catalog = new Catalog();
        catalog.setName(name);

        catalogRepository.save(catalog);

        return entityMapper.mapCatalogToCatalogDTO(catalog);
    }

    public FlashCardDTO createFlashCard(Long catalogId, WordTranslation wordTranslation) {
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow(() ->
                new EntityNotFoundException("Not found catalog"));

        if (flashCardRepository.findByWordIgnoreCase(wordTranslation.getWord()).isPresent()) {
            throw new ExistsException("This word exists");
        }
        String translation = translationService.translateWord(wordTranslation);
        FlashCard flashCard = new FlashCard();
        flashCard.setWord(wordTranslation.getWord());
        flashCard.setTranslation(translation);
        flashCard.setCatalog(catalog);
        catalog.getFlashCards().add(flashCard);

        flashCardRepository.save(flashCard);

        return entityMapper.mapFlashCardToFlashCardDTO(flashCard);
    }

    public List<FlashCardDTO> findFlashCardsByCatalogId(Long catalogId) {
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow(() ->
                new EntityNotFoundException("Not found catalog"));

        List<FlashCard> flashCardList = catalog.getFlashCards();
        if (flashCardList.isEmpty()) {
            throw new EntityNotFoundException("Catalog is empty");
        }
        return entityMapper.mapFlashCardListToFlashCardListDTO(flashCardList);
    }

    public void deleteCatalogById(Long id) {
        Catalog catalog = catalogRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Catalog not found"));

        catalogRepository.delete(catalog);
    }

    public void deleteAllCatalogs() {
        catalogRepository.deleteAll();
    }
}

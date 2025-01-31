package com.example.SongLingo.flashcards.flashCard;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.flashcards.catalog.Catalog;
import com.example.SongLingo.flashcards.catalog.CatalogRepository;
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

    public FlashCardDTO createFlashCard(FlashCardRequest flashCardRequest) {
        Catalog catalog = catalogRepository.findById(flashCardRequest.getCatalogId()).orElseThrow(() ->
                new EntityNotFoundException("Catalog not found"));

        if (flashCardRepository.findByWordIgnoreCase(flashCardRequest.getWord()).isPresent()) {
            throw new ExistsException("This word exists");
        }
        WordTranslation wordTranslation = new WordTranslation(flashCardRequest.getWord(), flashCardRequest.getLanguage());

        String translation = translationService.translateWord(wordTranslation);
        FlashCard flashCard = new FlashCard();
        flashCard.setWord(flashCardRequest.getWord());
        flashCard.setTranslation(translation);
        flashCard.setCatalog(catalog);
        catalog.getFlashCards().add(flashCard);

        flashCardRepository.save(flashCard);

        return entityMapper.mapFlashCardToFlashCardDTO(flashCard);
    }

    public List<FlashCardDTO> findFlashCardsByCatalogId(Long catalogId) {
        List<FlashCard> flashCardList = flashCardRepository.findByCatalogId(catalogId);
        if (flashCardList.isEmpty()) {
            throw new EntityNotFoundException("Catalog is empty");
        }
        return entityMapper.mapFlashCardListToFlashCardListDTO(flashCardList);
    }
}

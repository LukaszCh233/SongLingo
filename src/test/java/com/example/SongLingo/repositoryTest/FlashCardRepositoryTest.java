package com.example.SongLingo.repositoryTest;

import com.example.SongLingo.flashcards.entity.Catalog;
import com.example.SongLingo.flashcards.entity.FlashCard;
import com.example.SongLingo.flashcards.repository.CatalogRepository;
import com.example.SongLingo.flashcards.repository.FlashCardRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class FlashCardRepositoryTest {
    @Autowired
    FlashCardRepository flashCardRepository;
    @Autowired
    CatalogRepository catalogRepository;

    @BeforeEach
    public void setUp() {
        flashCardRepository.deleteAll();
    }

    @Test
    public void findFlashCardByWordIgnoreCase() {
        newFlashCardWithCatalog("word");

        Optional<FlashCard> foundFlashCard = flashCardRepository.findByWordIgnoreCase("WORD");

        Assertions.assertTrue(foundFlashCard.isPresent());
        Assertions.assertEquals(foundFlashCard.get().getWord(), "word");
    }

    private void newFlashCardWithCatalog(String word) {
        Catalog catalog = new Catalog();
        catalog.setName("catalog");

        catalogRepository.save(catalog);

        FlashCard flashCard = new FlashCard();
        flashCard.setCatalog(catalog);
        flashCard.setWord(word);
        flashCard.setTranslation("word");

        flashCardRepository.save(flashCard);
    }
}

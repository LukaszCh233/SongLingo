package com.example.SongLingo.flashcards.repository;

import com.example.SongLingo.flashcards.entity.FlashCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {
    Optional<FlashCard> findByWordIgnoreCase(String word);
}

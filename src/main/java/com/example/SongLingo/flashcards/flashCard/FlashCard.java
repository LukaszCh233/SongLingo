package com.example.SongLingo.flashcards.flashCard;

import com.example.SongLingo.flashcards.catalog.Catalog;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FlashCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "catalog_id")
    Catalog catalog;
    String word;
    String translation;
}

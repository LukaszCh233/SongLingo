package com.example.SongLingo.flashcards.entity;

import com.example.SongLingo.flashcards.entity.Catalog;
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

package com.example.SongLingo.flashcards.catalog;

import com.example.SongLingo.flashcards.flashCard.FlashCard;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Catalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @OneToMany(mappedBy = "catalog")
    List<FlashCard> flashCards;
}


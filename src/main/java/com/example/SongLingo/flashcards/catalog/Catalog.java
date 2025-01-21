package com.example.SongLingo.flashcards.catalog;

import com.example.SongLingo.flashcards.flashCard.FlashCard;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "name cannot be blank")
    String name;
    @OneToMany(mappedBy = "catalog", cascade = CascadeType.REMOVE)
    List<FlashCard> flashCards;
}


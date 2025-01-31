package com.example.SongLingo.flashcards.flashCard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FlashCardRequest {
    @NotNull(message = "Catalog  ID must be provided")
    private Long catalogId;
    @NotBlank(message = "word cannot be blank")
    private String word;
    @NotBlank(message = "language cannot be blank")
    private String language;
}


package com.example.SongLingo.flashcards.catalog;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CatalogRequest {
    @NotBlank(message = "name cannot be blank")
    private String name;
}

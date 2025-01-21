package com.example.SongLingo.translate;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WordTranslation {
    @NotBlank(message = "word cannot be blank")
    private String word;
    @NotBlank(message = "language cannot be blank")
    private String language;
}

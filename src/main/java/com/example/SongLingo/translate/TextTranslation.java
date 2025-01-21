package com.example.SongLingo.translate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TextTranslation {
    @NotBlank(message = "Text must not be blank")
    private String text;
    @NotBlank(message = "Language must not be blank")
    private String language;
}

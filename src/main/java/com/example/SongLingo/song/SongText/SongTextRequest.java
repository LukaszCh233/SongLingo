package com.example.SongLingo.song.SongText;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SongTextRequest {
    @NotBlank(message = "Text cannot be blank")
    String text;
}

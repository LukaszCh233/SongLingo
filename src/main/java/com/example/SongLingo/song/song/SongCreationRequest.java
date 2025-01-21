package com.example.SongLingo.song.song;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SongCreationRequest {
    @NotBlank(message = "Title must not be blank")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters")
    private String title;

    @NotBlank(message = "Author must not be blank")
    @Size(min = 1, max = 100, message = "Author must be between 1 and 100 characters")
    private String author;

    @NotNull(message = "Category ID must not be null")
    private Long categoryId;
}

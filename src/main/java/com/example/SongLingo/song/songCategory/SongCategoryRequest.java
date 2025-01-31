package com.example.SongLingo.song.songCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongCategoryRequest {
    @NotBlank(message = "Name must not be blank")
    private String name;
}

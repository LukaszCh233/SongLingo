package com.example.SongLingo.song.song;

import com.example.SongLingo.song.SongText.SongText;
import com.example.SongLingo.song.songCategory.SongCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "song_text_id")
    private SongText songText = new SongText();
    @ManyToOne
    private SongCategory songCategory;
}

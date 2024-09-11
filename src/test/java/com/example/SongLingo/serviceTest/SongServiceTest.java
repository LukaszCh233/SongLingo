package com.example.SongLingo.serviceTest;

import com.example.SongLingo.song.dto.SongDTO;
import com.example.SongLingo.song.dto.SongTextDTO;
import com.example.SongLingo.song.entity.Song;
import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.entity.SongText;
import com.example.SongLingo.song.repository.SongCategoryRepository;
import com.example.SongLingo.song.repository.SongRepository;
import com.example.SongLingo.song.service.SongService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class SongServiceTest {

    @Autowired
    SongCategoryRepository songCategoryRepository;
    @Autowired
    SongRepository songRepository;
    @Autowired
    SongService songService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void createdSongShouldBeFindInSongList_test() {
            SongCategory songCategory = newSongCategory("category");

        songService.createSong("title", "author",songCategory.getId());

        List<SongDTO> songList = songService.findAllSongs();

        assertEquals(songList.size(), 1);
        assertEquals(songList.get(0).title(), "title");
    }
    @Test
    public void canAddTextToExistsSongAndDisplayThisText_test() {
        Song song = newSong();

        SongText songText = new SongText();
        songText.setText("text");

        songService.addTextToSong(song.getId(),songText);

        SongTextDTO foundSongText = songService.findSongTextBySongId(song.getId());

        assertEquals(foundSongText.text(), "text");
    }

    private Song newSong() {
        SongCategory songCategory = new SongCategory();
        songCategory.setName("category");

        songCategoryRepository.save(songCategory);

        Song song = new Song();
        song.setSongCategory(songCategory);
        song.setAuthor("author");
        song.setTitle("title");

      return  songRepository.save(song);
    }

    private SongCategory newSongCategory(String name) {
        SongCategory songCategory = new SongCategory();
        songCategory.setName(name);

        return songCategoryRepository.save(songCategory);
    }
}

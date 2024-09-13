package com.example.SongLingo.repositoryTest;

import com.example.SongLingo.song.entity.Song;
import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.repository.SongCategoryRepository;
import com.example.SongLingo.song.repository.SongRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
public class SongRepositoryTest {
    @Autowired
    SongRepository songRepository;
    @Autowired
    SongCategoryRepository songCategoryRepository;

    @BeforeEach
    public void setUp() {
        songRepository.deleteAll();
    }

    @Test
    public void findSongsByCategoryNameIgnoreCase() {
        newSongWithCategory("title", "author", "category");
        newSongWithCategory("title1", "author1", "category");

        List<Song> foundSongs = songRepository.findBySongCategoryNameIgnoreCase("CATEGORY");

        assertFalse(foundSongs.isEmpty());
        assertEquals(2, foundSongs.size());
        assertEquals(foundSongs.get(0).getTitle(), "title");
    }

    @Test
    public void findSongsByTitleIgnoreCase() {
        newSongWithCategory("title", "author", "category");

        List<Song> foundSongs = songRepository.findByTitleIgnoreCase("TITLE");

        assertFalse(foundSongs.isEmpty());
        assertEquals(1, foundSongs.size());
        assertEquals(foundSongs.get(0).getTitle(), "title");
    }

    @Test
    public void findSongsByAuthorIgnoreCase() {
        newSongWithCategory("title", "author", "category");

        List<Song> foundSongs = songRepository.findByAuthorIgnoreCase("AUTHOR");

        assertFalse(foundSongs.isEmpty());
        assertEquals(1, foundSongs.size());
        assertEquals(foundSongs.get(0).getAuthor(), "author");
    }

    private void newSongWithCategory(String title, String author, String categoryName) {
        SongCategory songCategory = new SongCategory();
        songCategory.setName(categoryName);

        songCategoryRepository.save(songCategory);

        Song song = new Song();
        song.setTitle(title);
        song.setAuthor(author);
        song.setSongCategory(songCategory);

        songRepository.save(song);
    }
}

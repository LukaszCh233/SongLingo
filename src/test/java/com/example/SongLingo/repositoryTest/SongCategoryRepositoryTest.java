package com.example.SongLingo.repositoryTest;

import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.repository.SongCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class SongCategoryRepositoryTest {
    @Autowired
    SongCategoryRepository songCategoryRepository;

    @Test
    public void findSongCategoryIgnoreCase() {
        newSongCategory("testCategory");

        Optional<SongCategory> foundSongCategory = songCategoryRepository.findByNameIgnoreCase("TestCategory");

        assertTrue(foundSongCategory.isPresent());
        assertEquals(foundSongCategory.get().getName(), "testCategory");
    }

    private void newSongCategory(String name) {
        SongCategory songCategory = new SongCategory();
        songCategory.setName(name);

        songCategoryRepository.save(songCategory);
    }
}

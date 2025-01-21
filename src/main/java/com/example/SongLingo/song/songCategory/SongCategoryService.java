package com.example.SongLingo.song.songCategory;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.mapper.EntityMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongCategoryService {
    private final SongCategoryRepository songCategoryRepository;
    private final EntityMapper entityMapper;

    public SongCategoryService(SongCategoryRepository songCategoryRepository, EntityMapper entityMapper) {
        this.songCategoryRepository = songCategoryRepository;
        this.entityMapper = entityMapper;
    }

    public SongCategory createSongCategory(String name) {

        if (songCategoryRepository.findByNameIgnoreCase(name).isPresent()) {
            throw new ExistsException("Category exists");
        }
        SongCategory songCategory = new SongCategory();
        songCategory.setName(name);

        return songCategoryRepository.save(songCategory);
    }

    public List<SongCategoryDTO> findAllSongCategory() {
        List<SongCategory> songCategoryList = songCategoryRepository.findAll();
        if (songCategoryList.isEmpty()) {
            throw new EntityNotFoundException("Category list is empty");
        }
        return entityMapper.mapSongCategoriesToSongCategoriesDTO(songCategoryList);
    }

    public void deleteSongCategoryById(Long songCategoryId) {
        Optional<SongCategory> songCategory = songCategoryRepository.findById(songCategoryId);
        songCategory.ifPresent(songCategoryRepository::delete);
    }

    public void deleteAllSongCategories() {
        songCategoryRepository.deleteAll();
    }

    public void updateSongCategory(Long songCategoryId, String name) {
        SongCategory existsSongCategory = songCategoryRepository.findById(songCategoryId).orElseThrow(() ->
                new EntityNotFoundException("Category not found"));
        existsSongCategory.setName(name);

        songCategoryRepository.save(existsSongCategory);
    }
}

package com.example.SongLingo.song.service;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.song.dto.SongCategoryDTO;
import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.repository.SongCategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongCategoryService {
    private final SongCategoryRepository songCategoryRepository;
    private final EntityMapper entityMapper;

    public SongCategoryService(SongCategoryRepository songCategoryRepository, EntityMapper entityMapper) {
        this.songCategoryRepository = songCategoryRepository;
        this.entityMapper = entityMapper;
    }

    public SongCategory createSongCategory(SongCategory songCategory) {
        if (songCategoryRepository.findByNameIgnoreCase(songCategory.getName()).isPresent()) {
            throw new ExistsException("Category exists");
        }
        return songCategoryRepository.save(songCategory);
    }

    public List<SongCategoryDTO> findAllSongCategory() {
        List<SongCategory> songCategoryList = songCategoryRepository.findAll();
        if (songCategoryList.isEmpty()) {
            throw new EntityNotFoundException("Category list is empty");
        }
        return entityMapper.mapSongCategoriesToSongCategoriesDTO(songCategoryList);
    }

    public void deleteSongCategoryById(Long id) {
        SongCategory songCategory = songCategoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Category not found"));

        songCategoryRepository.delete(songCategory);
    }

    public void deleteAllSongCategories() {
        long count = songCategoryRepository.count();
        if (count == 0) {
            throw new EntityNotFoundException("Category list is empty");
        }
        songCategoryRepository.deleteAll();
    }

    public void updateSongCategory(Long id, SongCategory songCategory) {
        SongCategory existsSongCategory = songCategoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Category not found"));
        existsSongCategory.setName(songCategory.getName());

        songCategoryRepository.save(existsSongCategory);
    }
}

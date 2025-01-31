package com.example.SongLingo.song.songCategory;

import com.example.SongLingo.exception.ExistsException;
import com.example.SongLingo.exception.OperationNotAllowedException;
import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.song.song.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongCategoryService {
    private final SongCategoryRepository songCategoryRepository;
    private final SongRepository songRepository;
    private final EntityMapper entityMapper;

    public SongCategoryService(SongCategoryRepository songCategoryRepository, EntityMapper entityMapper,
                               SongRepository songRepository) {
        this.songCategoryRepository = songCategoryRepository;
        this.songRepository = songRepository;
        this.entityMapper = entityMapper;
    }

    public SongCategory createSongCategory(SongCategoryRequest songCategoryRequest) {
        if (songCategoryRepository.findByNameIgnoreCase(songCategoryRequest.getName()).isPresent()) {
            throw new ExistsException("Category exists");
        }
        SongCategory songCategory = new SongCategory();
        songCategory.setName(songCategoryRequest.getName());

        return songCategoryRepository.save(songCategory);
    }

    public List<SongCategoryDTO> findAllSongsCategory() {
        List<SongCategory> songCategoryList = songCategoryRepository.findAll();
        if (songCategoryList.isEmpty()) {
            throw new EntityNotFoundException("Category list is empty");
        }
        return entityMapper.mapSongCategoriesToSongCategoriesDTO(songCategoryList);
    }

    public void deleteSongCategoryById(Long songCategoryId) {
        SongCategory songCategory = songCategoryRepository.findById(songCategoryId).orElseThrow(() ->
                new EntityNotFoundException("Category not found"));
        if (songRepository.existsBySongCategoryId(songCategoryId)) {
            throw new OperationNotAllowedException("You cannot delete the category. The category contains songs.");
        }
        songCategoryRepository.delete(songCategory);
    }

    public void deleteAllSongCategories() {
        if (songRepository.findFirstBy().isPresent()) {
            throw new OperationNotAllowedException("You cannot delete the categories. The categories contains songs.");
        }
        songCategoryRepository.deleteAll();
    }

    public void updateSongCategory(Long songCategoryId, SongCategoryRequest songCategoryRequest) {
        SongCategory categoryToUpdate = songCategoryRepository.findById(songCategoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category with ID " + songCategoryId + " not found"));

        categoryToUpdate.setName(songCategoryRequest.getName());

        songCategoryRepository.save(categoryToUpdate);
    }
}

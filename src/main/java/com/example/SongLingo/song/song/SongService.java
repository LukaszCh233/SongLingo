package com.example.SongLingo.song.song;

import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.song.songCategory.SongCategory;
import com.example.SongLingo.song.songCategory.SongCategoryRepository;
import com.example.SongLingo.song.songCategory.SongCategoryRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongCategoryRepository songCategoryRepository;
    private final EntityMapper entityMapper;

    public SongService(SongRepository songRepository, SongCategoryRepository songCategoryRepository,
                       EntityMapper entityMapper) {
        this.songRepository = songRepository;
        this.songCategoryRepository = songCategoryRepository;
        this.entityMapper = entityMapper;
    }

    public SongDTO createSong(SongCreationRequest songCreationRequest) {
        SongCategory category = songCategoryRepository.findById((songCreationRequest.getCategoryId())).orElseThrow(()
                -> new EntityNotFoundException("Category not found"));

        Song createdSong = new Song();
        createdSong.setTitle(songCreationRequest.getTitle());
        createdSong.setAuthor(songCreationRequest.getAuthor());
        createdSong.setSongCategory(category);

        songRepository.save(createdSong);

        return entityMapper.mapSongToSongDTO(createdSong);
    }

    public List<SongDTO> findAllSongs() {
        List<Song> songList = songRepository.findAll();
        if (songList.isEmpty()) {
            throw new EntityNotFoundException("Song list is empty");
        }
        return entityMapper.mapSongsToSongsDTO(songList);
    }

    public List<SongDTO> findSongsByCategoryName(SongCategoryRequest songCategoryRequest) {
        List<Song> songsOfCategory = songRepository.findBySongCategoryNameIgnoreCase(songCategoryRequest.getName());
        if (songsOfCategory.isEmpty()) {
            throw new EntityNotFoundException("Songs not found");
        }
        return entityMapper.mapSongsToSongsDTO(songsOfCategory);
    }

    public List<SongDTO> findSongsByTitle(String title) {
        List<Song> songList = songRepository.findByTitleIgnoreCase(title);
        if (songList.isEmpty()) {
            throw new EntityNotFoundException("Songs not found");
        }
        return entityMapper.mapSongsToSongsDTO(songList);
    }

    public List<SongDTO> findSongsByAuthor(String author) {
        List<Song> songList = songRepository.findByAuthorIgnoreCase(author);
        if (songList.isEmpty()) {
            throw new EntityNotFoundException("Songs not found");
        }
        return entityMapper.mapSongsToSongsDTO(songList);
    }

    public void deleteSongById(Long songId) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException("Song not found"));

        songRepository.delete(song);
    }

    public void deleteAllSongs() {
        songRepository.deleteAll();
    }

    public void updateSong(Long idSong, SongCreationRequest songCreationRequest) {
        Song songToUpdate = songRepository.findById(idSong).orElseThrow(() ->
                new EntityNotFoundException("Song not found"));

        SongCategory songCategory = songCategoryRepository.findById(songCreationRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        songToUpdate.setTitle(songCreationRequest.getTitle());
        songToUpdate.setAuthor(songCreationRequest.getAuthor());
        songToUpdate.setSongCategory(songCategory);

        songRepository.save(songToUpdate);
    }
}

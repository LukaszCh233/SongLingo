package com.example.SongLingo.song.song;

import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.song.songCategory.SongCategory;
import com.example.SongLingo.song.songCategory.SongCategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<SongDTO> findSongsByCategoryName(String name) {
        List<Song> songsOfCategory = songRepository.findBySongCategoryNameIgnoreCase(name);
        if (songsOfCategory.isEmpty()) {
            throw new EntityNotFoundException("Not found songs");
        }
        return entityMapper.mapSongsToSongsDTO(songsOfCategory);
    }

    public List<SongDTO> findSongsByTitle(String title) {
        List<Song> songList = songRepository.findByTitleIgnoreCase(title);
        if (songList.isEmpty()) {
            throw new EntityNotFoundException("Not found songs");
        }
        return entityMapper.mapSongsToSongsDTO(songList);
    }

    public List<SongDTO> findSongsByAuthor(String author) {
        List<Song> songList = songRepository.findByAuthorIgnoreCase(author);
        if (songList.isEmpty()) {
            throw new EntityNotFoundException("Not found songs");
        }
        return entityMapper.mapSongsToSongsDTO(songList);
    }

    public void deleteSongById(Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        song.ifPresent(songRepository::delete);
    }

    public void deleteAllSongs() {
        songRepository.deleteAll();
    }

    public SongDTO updateSong(Long idSong, SongCreationRequest songCreationRequest) {
        Song songToUpdate = songRepository.findById(idSong).orElseThrow(() ->
                new EntityNotFoundException("song not found"));

        SongCategory songCategory = songCategoryRepository.findById(songCreationRequest.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        songToUpdate.setTitle(songCreationRequest.getTitle());
        songToUpdate.setAuthor(songCreationRequest.getAuthor());
        songToUpdate.setSongCategory(songCategory);

        songRepository.save(songToUpdate);

        return entityMapper.mapSongToSongDTO(songToUpdate);
    }
}

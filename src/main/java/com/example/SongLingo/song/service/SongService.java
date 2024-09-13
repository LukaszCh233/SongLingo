package com.example.SongLingo.song.service;

import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.song.dto.SongDTO;
import com.example.SongLingo.song.dto.SongTextDTO;
import com.example.SongLingo.song.entity.Song;
import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.entity.SongText;
import com.example.SongLingo.song.repository.SongCategoryRepository;
import com.example.SongLingo.song.repository.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    public SongDTO createSong(String title, String author, Long categoryId) {
        SongCategory category = songCategoryRepository.findById((categoryId)).orElseThrow(()
                -> new EntityNotFoundException("Category not found"));
        Song createdSong = new Song();

        createdSong.setTitle(title);
        createdSong.setAuthor(author);
        createdSong.setSongCategory(category);

        songRepository.save(createdSong);
        return entityMapper.mapSongToSongDTO(createdSong);
    }

    @Transactional
    public void addTextToSong(Long songId, SongText songText) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException("Not found song"));

        song.setSongText(songText);

        songRepository.save(song);
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

    public SongTextDTO findSongTextBySongId(Long songId) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException("Song not found"));

        return entityMapper.mapSongTextToSongTextDTO(song.getSongText());
    }

    public void deleteSongById(Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Song not found"));

        songRepository.delete(song);
    }

    public void deleteAllSongs() {
        long count = songRepository.count();
        if (count == 0) {
            throw new EntityNotFoundException("Song list is empty");
        }
        songRepository.deleteAll();
    }

    public SongDTO updateSong(Long idSong, SongDTO song) {
        Song songToUpdate = songRepository.findById(idSong).orElseThrow(() -> new EntityNotFoundException("song not found"));

        songToUpdate.setTitle(song.title());
        songToUpdate.setAuthor(song.author());
        songToUpdate.getSongCategory().setName(song.category());

        songRepository.save(songToUpdate);

        return entityMapper.mapSongToSongDTO(songToUpdate);
    }
}

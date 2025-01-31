package com.example.SongLingo.song.SongText;

import com.example.SongLingo.mapper.EntityMapper;
import com.example.SongLingo.song.song.Song;
import com.example.SongLingo.song.song.SongRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SongTextService {
    private final SongRepository songRepository;
    private final EntityMapper entityMapper;

    public SongTextService(SongRepository songRepository, EntityMapper entityMapper) {
        this.songRepository = songRepository;
        this.entityMapper = entityMapper;
    }

    public SongTextDTO findSongTextBySongId(Long songId) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException("Song not found"));

        return entityMapper.mapSongTextToSongTextDTO(song.getSongText());
    }

    public void createSongText(Long songId, SongTextRequest songTextRequest) {
        Song song = songRepository.findById(songId).orElseThrow(() -> new EntityNotFoundException("Song not found"));

        song.getSongText().setText(songTextRequest.getText());

        songRepository.save(song);
    }
}

package com.example.SongLingo.mapper;

import com.example.SongLingo.flashcards.entity.Catalog;
import com.example.SongLingo.flashcards.dto.CatalogDTO;
import com.example.SongLingo.flashcards.entity.FlashCard;
import com.example.SongLingo.flashcards.dto.FlashCardDTO;
import com.example.SongLingo.song.dto.SongCategoryDTO;
import com.example.SongLingo.song.dto.SongDTO;
import com.example.SongLingo.song.dto.SongTextDTO;
import com.example.SongLingo.song.entity.Song;
import com.example.SongLingo.song.entity.SongCategory;
import com.example.SongLingo.song.entity.SongText;
import com.example.SongLingo.user.dto.AdminDTO;
import com.example.SongLingo.user.dto.UserDTO;
import com.example.SongLingo.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityMapper {
    public SongCategoryDTO mapSongCategoryToSongCategoryDTO(SongCategory songCategory) {
        return new SongCategoryDTO(songCategory.getName());
    }

    public List<SongCategoryDTO> mapSongCategoriesToSongCategoriesDTO(List<SongCategory> songCategoryList) {
        return songCategoryList.stream()
                .map(this::mapSongCategoryToSongCategoryDTO)
                .collect(Collectors.toList());
    }

    public SongDTO mapSongToSongDTO(Song song) {
        return new SongDTO(song.getTitle(), song.getAuthor(), song.getSongCategory().getName());
    }

    public List<SongDTO> mapSongsToSongsDTO(List<Song> songList) {
        return songList.stream()
                .map(this::mapSongToSongDTO)
                .collect(Collectors.toList());
    }

    public SongTextDTO mapSongTextToSongTextDTO(SongText songText) {
        return new SongTextDTO(songText.getText());
    }

    public AdminDTO mapAdminToAdminDTO(User admin) {
        return new AdminDTO(admin.getId(), admin.getName(), admin.getEmail());
    }

    public UserDTO mapUserToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    public List<AdminDTO> mapAdminListToAdminListDTO(List<User> adminList) {
        return adminList.stream()
                .map(this::mapAdminToAdminDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> mapUserListToUserListDTO(List<User> userList) {
        return userList.stream()
                .map(this::mapUserToUserDTO)
                .collect(Collectors.toList());
    }
    public FlashCardDTO mapFlashCardToFlashCardDTO(FlashCard flashCard) {
        return new FlashCardDTO(flashCard.getWord(), flashCard.getTranslation());
    }
    public List<FlashCardDTO> mapFlashCardListToFlashCardListDTO(List<FlashCard> flashCardList) {
        return  flashCardList.stream()
                .map(this::mapFlashCardToFlashCardDTO)
                .collect(Collectors.toList());
    }
    public CatalogDTO mapCatalogToCatalogDTO(Catalog catalog) {
        return new CatalogDTO(catalog.getId(), catalog.getName());
    }
    public List<CatalogDTO> mapCatalogListToCatalogListDTO(List<Catalog> catalogList) {
        return catalogList.stream()
                .map(this::mapCatalogToCatalogDTO)
                .collect(Collectors.toList());
    }

}

package com.example.SongLingo.translate;

import com.example.SongLingo.song.SongText.SongTextDTO;
import com.example.SongLingo.song.SongText.SongTextService;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.Translation;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {
    private final SongTextService songTextService;
    private final Translate translate;

    public TranslationService(SongTextService songTextService, Translate translate) {
        this.songTextService = songTextService;
        this.translate = translate;
    }

    public String translateSongText(Long songId, String language) {
        SongTextDTO songText = songTextService.findSongTextBySongId(songId);

        TextTranslation textTranslation = new TextTranslation(songText.text(), language);

        return translateText(textTranslation);
    }

    public String translateWord(WordTranslation wordTranslation) {
        Translation translation = translate.translate(
                wordTranslation.getWord(),
                Translate.TranslateOption.targetLanguage(wordTranslation.getLanguage()),
                Translate.TranslateOption.format("text")
        );
        return translation.getTranslatedText();
    }

    private String translateText(TextTranslation textTranslation) {
        Translation translation = translate.translate(
                textTranslation.getText(),
                Translate.TranslateOption.targetLanguage(textTranslation.getLanguage()),
                Translate.TranslateOption.format("text")
        );
        return translation.getTranslatedText();
    }
}

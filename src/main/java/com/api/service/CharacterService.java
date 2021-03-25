package com.api.service;

import com.api.entity.CharacterEntity;

import java.util.List;

public interface CharacterService {

    CharacterEntity getRandomCharacter();
    List<CharacterEntity> getCharactersLikeNames(String likeName);
    void deleteAllCharacters();
    void synchronizeCharacters(List<CharacterEntity> allCharacters);
    List<CharacterEntity> saveAll(List<CharacterEntity> characterEntityList);
}

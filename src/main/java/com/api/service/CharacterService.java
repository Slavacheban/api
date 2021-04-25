package com.api.service;

import com.api.entity.Character;

import java.util.List;

public interface CharacterService {

    Character getRandomCharacter();
    List<Character> getCharactersLikeNames(String likeName);
    void deleteAllCharacters();
    void synchronizeCharacters(List<Character> allCharacters);
    List<Character> saveAll(List<Character> characterList);
}

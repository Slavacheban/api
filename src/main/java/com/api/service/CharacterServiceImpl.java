package com.api.service;

import com.api.dao.CharacterDAO;
import com.api.entity.Character;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterServiceImpl implements CharacterService {

    private final CharacterDAO characterDAO;

    @Override
    public Character getRandomCharacter() {
        long count = characterDAO.count();
        int random = (int) Math.round(Math.random() * count);
        Page<Character> characterEntityPage = characterDAO.findAll(PageRequest.of(random, 1));
        Character result = null;
        if (characterEntityPage.hasContent()) {
            result = characterEntityPage.getContent().get(0);
        }
        return result;
    }

    @Override
    public List<Character> getCharactersLikeNames(String likeName) {
        return characterDAO.getCharacterEntitiesByNameContains(likeName);
    }

    @Override
    public void deleteAllCharacters() {
        characterDAO.deleteAll();
    }

    @Override
    @Transactional
    public void synchronizeCharacters(List<Character> allCharacters) {
        deleteAllCharacters();
        saveAll(allCharacters);
    }

    @Override
    public List<Character> saveAll(List<Character> characterList) {
        return characterDAO.saveAll(characterList);
    }
}

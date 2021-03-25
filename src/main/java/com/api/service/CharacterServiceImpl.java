package com.api.service;

import com.api.dao.CharacterDAO;
import com.api.entity.CharacterEntity;
import com.api.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CharacterServiceImpl implements CharacterService {

    private CharacterDAO characterDAO;

    @Override
    public CharacterEntity getRandomCharacter() {
        long count = characterDAO.count();
        int random = (int) Math.round(Math.random() * count);
        Page<CharacterEntity> characterEntityPage = characterDAO.findAll(PageRequest.of(random, 1));
        CharacterEntity result = null;
        if (characterEntityPage.hasContent()) {
            result = characterEntityPage.getContent().get(0);
        }
        return result;
    }

    @Override
    public List<CharacterEntity> getCharactersLikeNames(String likeName) {
        return characterDAO.getCharacterEntitiesByNameContains(likeName);
    }

    @Override
    public void deleteAllCharacters() {
        characterDAO.deleteAll();
    }

    @Override
    @Transactional
    public void synchronizeCharacters(List<CharacterEntity> allCharacters) {
        deleteAllCharacters();
        saveAll(allCharacters);
    }

    @Override
    public List<CharacterEntity> saveAll(List<CharacterEntity> characterEntityList) {
        return characterDAO.saveAll(characterEntityList);
    }
}

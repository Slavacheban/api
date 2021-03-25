package com.api.controller;

import com.api.entity.CharacterEntity;
import com.api.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RickAndMortyController {

    private CharacterService characterService;

    @RequestMapping(value = "randomOne")
    public CharacterEntity getRandomHero() {
        return characterService.getRandomCharacter();
    }

    @RequestMapping(value = "allByName/{likeName}")
    public List<CharacterEntity> getAllByName(@PathVariable("likeName") String likeName) {
        return characterService.getCharactersLikeNames(likeName);
    }

}

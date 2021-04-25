package com.api.controller;

import com.api.entity.Character;
import com.api.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/characters")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RickAndMortyController {

    private final CharacterService characterService;

    @RequestMapping(value = "/random-one")
    public Character getRandomHero() {
        return characterService.getRandomCharacter();
    }

    @RequestMapping("")
    public List<Character> getAllByName(@RequestParam(name = "like-name") String likeName) {
        return characterService.getCharactersLikeNames(likeName);
    }

}

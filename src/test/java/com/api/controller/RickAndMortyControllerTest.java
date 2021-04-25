package com.api.controller;

import com.api.entity.Character;
import com.api.service.CharacterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RickAndMortyControllerTest {

    @Autowired
    private RickAndMortyController controller;


    @Test
    void getRandomHero() {
        Character randomHero1 = controller.getRandomHero();
        Character randomHero2 = controller.getRandomHero();
        Assertions.assertNotEquals(randomHero1, randomHero2);
    }

    @Test
    void getAllByName() {
        String likeName = "Alien";
        List<Character> allByName = controller.getAllByName(likeName);
        for (Character character : allByName) {
            Assertions.assertTrue(character.getName().contains(likeName));
        }
    }
}

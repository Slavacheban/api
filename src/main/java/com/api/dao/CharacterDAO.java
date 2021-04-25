package com.api.dao;

import com.api.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterDAO extends JpaRepository<Character, Long> {
    long count();
    List<Character> getCharacterEntitiesByNameContains(String likeName);
}

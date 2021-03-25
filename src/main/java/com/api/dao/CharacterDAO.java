package com.api.dao;

import com.api.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterDAO extends JpaRepository<CharacterEntity, Long> {
    long count();
    List<CharacterEntity> getCharacterEntitiesByNameContains(String likeName);
}

package com.api.scheduler;

import com.api.entity.Character;
import com.api.service.CharacterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SynchronizeRickMorty {

    private static final String CRON = "0 0 1 * * ?";
    private final CharacterService characterService;

    @Scheduled(cron = CRON)
    public void synchronizeRickAndMortyData() {
        RickAndMortyService rickAndMortyService = new RickAndMortyService();
        List<Character> allCharacters = rickAndMortyService.getAllCharacters();
        characterService.synchronizeCharacters(allCharacters);
    }
}

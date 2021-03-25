package com.api.scheduler;

import com.api.entity.CharacterEntity;
import com.api.service.CharacterService;
import com.api.service.RickAndMortyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SynchronizeRickMorty {

    private static final String CRON = "0 0 */2 * * *";
    private final CharacterService characterService;

    @Scheduled(cron = CRON)
    public void synchronizeRickAndMortyData() {
        RickAndMortyService rickAndMortyService = new RickAndMortyService();
        try {
            List<CharacterEntity> allCharacters = rickAndMortyService.getAllCharacters();
            characterService.synchronizeCharacters(allCharacters);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

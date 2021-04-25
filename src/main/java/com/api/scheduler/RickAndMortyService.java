package com.api.scheduler;


import com.api.entity.Character;
import com.api.entity.Episode;
import com.api.entity.Location;
import com.api.entity.Origin;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RickAndMortyService {

    private static final String GET_ALL_CHARACTER_URL = "https://rickandmortyapi.com/api/character";

    public List<Character> getAllCharacters() {

        List<Character> characterList = new ArrayList<>();

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet characterRequest = new HttpGet(GET_ALL_CHARACTER_URL);

            try (CloseableHttpResponse characterResponse = httpClient.execute(characterRequest)) {
                HttpEntity entity = characterResponse.getEntity();
                if (entity != null) {
                    JSONObject json = new JSONObject(EntityUtils.toString(entity));
                    JSONArray jsonArray = json.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject characterJson = jsonArray.getJSONObject(i);
                        Character character = EntityMapper.getSimpleCharacterEntityFromJson(characterJson);
                        String locationUrl = characterJson.getJSONObject("location").getString("url");

                        if (locationUrl != null && !locationUrl.isEmpty()) {
                            HttpGet locationRequest = new HttpGet(locationUrl);
                            CloseableHttpResponse locationResponse = httpClient.execute(locationRequest);
                            JSONObject locationJson = new JSONObject(EntityUtils.toString(locationResponse.getEntity()));

                            Location location = EntityMapper.getSimpleLocationEntityFromJson(locationJson);
                            character.setLocation(location);
                        }

                        List<Episode> episodeEntities = new ArrayList<>();
                        JSONArray episodeArrayJson = characterJson.getJSONArray("episode");
                        for (int j = 0; j < episodeArrayJson.length(); j++) {
                            String episodeUrl = episodeArrayJson.getString(j);
                            HttpGet episodeRequest = new HttpGet(episodeUrl);
                            CloseableHttpResponse episodeResponse = httpClient.execute(episodeRequest);
                            JSONObject episodeJson = new JSONObject(EntityUtils.toString(episodeResponse.getEntity()));

                            Episode episode = EntityMapper.getSimpleEpisodeEntityFromJson(episodeJson);
                            episodeEntities.add(episode);
                        }
                        character.setEpisodes(episodeEntities);

                        characterList.add(character);
                    }
                }

            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return characterList;
    }
}

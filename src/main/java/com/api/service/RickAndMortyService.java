package com.api.service;


import com.api.entity.CharacterEntity;
import com.api.entity.EpisodeEntity;
import com.api.entity.LocationEntity;
import com.api.entity.OriginEntity;
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

public class RickAndMortyService {

    private static final String GET_ALL_CHARACTER_URL = "https://rickandmortyapi.com/api/character";

    public List<CharacterEntity> getAllCharacters() throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            List<CharacterEntity> characterEntityList = new ArrayList<>();
            HttpGet characterRequest = new HttpGet(GET_ALL_CHARACTER_URL);

            try (CloseableHttpResponse characterResponse = httpClient.execute(characterRequest)) {
                HttpEntity entity = characterResponse.getEntity();
                if (entity != null) {
                    JSONObject json = new JSONObject(EntityUtils.toString(entity));
                    JSONArray jsonArray = json.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject characterJson = jsonArray.getJSONObject(i);
                        CharacterEntity characterEntity = getSimpleCharacterEntityFromJson(characterJson);
                        String locationUrl = characterJson.getJSONObject("location").getString("url");

                        if (locationUrl != null && !locationUrl.isEmpty()) {
                            HttpGet locationRequest = new HttpGet(locationUrl);
                            CloseableHttpResponse locationResponse = httpClient.execute(locationRequest);
                            JSONObject locationJson = new JSONObject(EntityUtils.toString(locationResponse.getEntity()));

                            LocationEntity locationEntity = getSimpleLocationEntityFromJson(locationJson);
                            characterEntity.setLocation(locationEntity);
                        }

                        List<EpisodeEntity> episodeEntities = new ArrayList<>();
                        JSONArray episodeArrayJson = characterJson.getJSONArray("episode");
                        for (int j = 0; j < episodeArrayJson.length(); j++) {
                            String episodeUrl = episodeArrayJson.getString(j);
                            HttpGet episodeRequest = new HttpGet(episodeUrl);
                            CloseableHttpResponse episodeResponse = httpClient.execute(episodeRequest);
                            JSONObject episodeJson = new JSONObject(EntityUtils.toString(episodeResponse.getEntity()));

                            EpisodeEntity episodeEntity = getSimpleEpisodeEntityFromJson(episodeJson);
                            episodeEntities.add(episodeEntity);
                        }
                        characterEntity.setEpisodes(episodeEntities);

                        characterEntityList.add(characterEntity);
                    }
                }
                return characterEntityList;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private EpisodeEntity getSimpleEpisodeEntityFromJson(JSONObject episodeJson) {
        EpisodeEntity episodeEntity = new EpisodeEntity();
        episodeEntity.setEpisode(episodeJson.getString("episode"));
        episodeEntity.setAirDate(episodeJson.getString("air_date"));
        episodeEntity.setCreated(episodeJson.getString("created"));
        episodeEntity.setName(episodeJson.getString("name"));
        episodeEntity.setUrl(episodeJson.getString("url"));
        return episodeEntity;
    }

    private LocationEntity getSimpleLocationEntityFromJson(JSONObject locationJson) {
        LocationEntity locationEntity = new LocationEntity();
        locationEntity.setName(locationJson.getString("name"));
        locationEntity.setDimension(locationJson.getString("dimension"));
        locationEntity.setUrl(locationJson.getString("url"));
        locationEntity.setType(locationJson.getString("type"));
        locationEntity.setCreated(locationJson.getString("created"));
        return locationEntity;
    }

    private CharacterEntity getSimpleCharacterEntityFromJson(JSONObject characterJson) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(characterJson.getString("name"));
        characterEntity.setCreated(characterJson.getString("created"));
        characterEntity.setGender(characterJson.getString("gender"));
        characterEntity.setImage(characterJson.getString("image"));
        characterEntity.setSpecies(characterJson.getString("species"));
        characterEntity.setStatus(characterJson.getString("status"));
        characterEntity.setType(characterJson.getString("type"));
        characterEntity.setUrl(characterJson.getString("url"));
        characterEntity.setType(characterJson.getString("type"));

        OriginEntity originEntity = new OriginEntity();
        originEntity.setName(characterJson.getJSONObject("origin").getString("name"));
        originEntity.setUrl(characterJson.getJSONObject("origin").getString("url"));
        characterEntity.setOrigin(originEntity);

        return characterEntity;
    }
}

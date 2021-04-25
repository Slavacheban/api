package com.api.scheduler;

import com.api.entity.Character;
import com.api.entity.Episode;
import com.api.entity.Location;
import com.api.entity.Origin;
import org.json.JSONObject;

public class EntityMapper {
    public static Episode getSimpleEpisodeEntityFromJson(JSONObject episodeJson) {
        Episode episode = new Episode();
        episode.setEpisode(episodeJson.getString("episode"));
        episode.setAirDate(episodeJson.getString("air_date"));
        episode.setCreated(episodeJson.getString("created"));
        episode.setName(episodeJson.getString("name"));
        episode.setUrl(episodeJson.getString("url"));
        return episode;
    }

    public static Location getSimpleLocationEntityFromJson(JSONObject locationJson) {
        Location location = new Location();
        location.setName(locationJson.getString("name"));
        location.setDimension(locationJson.getString("dimension"));
        location.setUrl(locationJson.getString("url"));
        location.setType(locationJson.getString("type"));
        location.setCreated(locationJson.getString("created"));
        return location;
    }

    public static Character getSimpleCharacterEntityFromJson(JSONObject characterJson) {
        Character character = new Character();
        character.setName(characterJson.getString("name"));
        character.setCreated(characterJson.getString("created"));
        character.setGender(characterJson.getString("gender"));
        character.setImage(characterJson.getString("image"));
        character.setSpecies(characterJson.getString("species"));
        character.setStatus(characterJson.getString("status"));
        character.setType(characterJson.getString("type"));
        character.setUrl(characterJson.getString("url"));
        character.setType(characterJson.getString("type"));

        Origin origin = new Origin();
        origin.setName(characterJson.getJSONObject("origin").getString("name"));
        origin.setUrl(characterJson.getJSONObject("origin").getString("url"));
        character.setOrigin(origin);

        return character;
    }
}

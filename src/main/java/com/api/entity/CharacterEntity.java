package com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "character")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String status;

    private String species;

    private String type;

    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    private OriginEntity origin;

    @ManyToOne(cascade = CascadeType.ALL)
    private LocationEntity location;

    private String image;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "character_episode",
        joinColumns = {@JoinColumn(name = "character_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "episode_id", referencedColumnName = "id")})
    private List<EpisodeEntity> episodes = new ArrayList<>();

    private String url;

    private String created;

}

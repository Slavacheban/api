package com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "episode")
public class EpisodeEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(name = "air_date")
    private String airDate;

    private String episode;

    @ManyToMany(mappedBy = "episodes", fetch = FetchType.LAZY)
    private List<CharacterEntity> characters = new ArrayList<>();

    private String url;

    private String created;
}

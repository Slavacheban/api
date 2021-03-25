package com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "location")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;

    private String dimension;

    @OneToMany(mappedBy = "location")
    private List<CharacterEntity> residents = new ArrayList<>();

    private String url;

    private String created;

}


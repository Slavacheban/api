package com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "character")
public class Character {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String status;

    private String species;

    private String type;

    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    private Origin origin;

    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;

    private String image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "character_episode",
        joinColumns = {@JoinColumn(name = "character_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "episode_id", referencedColumnName = "id")})
    private List<Episode> episodes = new ArrayList<>();

    private String url;

    private String created;

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", image='" + image + '\'' +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}

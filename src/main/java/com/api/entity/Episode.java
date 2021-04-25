package com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "episode")
public class Episode {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @Column(name = "air_date")
    private String airDate;

    private String episode;

    @ManyToMany(mappedBy = "episodes", fetch = FetchType.LAZY)
    private List<Character> characters = new ArrayList<>();

    private String url;

    private String created;

    @Override
    public String toString() {
        return "Episode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", airDate='" + airDate + '\'' +
                ", episode='" + episode + '\'' +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}

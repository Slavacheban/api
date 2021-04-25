package com.api.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String type;

    private String dimension;

    @OneToMany(mappedBy = "location")
    private List<Character> residents = new ArrayList<>();

    private String url;

    private String created;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dimension='" + dimension + '\'' +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}


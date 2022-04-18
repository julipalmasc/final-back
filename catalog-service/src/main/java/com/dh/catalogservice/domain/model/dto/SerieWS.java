package com.dh.catalogservice.domain.model.dto;

import lombok.Data;

@Data
public class SerieWS {
    private Integer id;
    private String name;
    private String genre;
    private String seasons;

    public SerieWS(Integer id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }
}
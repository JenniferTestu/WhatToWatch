package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Credits implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("cast")
    private List<Cast> cast = null;
    @SerializedName("crew")
    private List<Crew> crew = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Credits withId(Integer id) {
        this.id = id;
        return this;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public Credits withCast(List<Cast> cast) {
        this.cast = cast;
        return this;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    public void setCrew(List<Crew> crew) {
        this.crew = crew;
    }

    public Credits withCrew(List<Crew> crew) {
        this.crew = crew;
        return this;
    }

}

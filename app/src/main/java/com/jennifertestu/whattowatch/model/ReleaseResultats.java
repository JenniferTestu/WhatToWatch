package com.jennifertestu.whattowatch.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseResultats {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<ReleaseCountry> results = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReleaseResultats withId(Integer id) {
        this.id = id;
        return this;
    }

    public List<ReleaseCountry> getResults() {
        return results;
    }

    public void setResults(List<ReleaseCountry> results) {
        this.results = results;
    }

    public ReleaseResultats withResults(List<ReleaseCountry> results) {
        this.results = results;
        return this;
    }

}
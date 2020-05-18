package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseDate {

    @SerializedName("certification")
    @Expose
    private String certification;
    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("type")
    @Expose
    private Integer type;

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public ReleaseDate withCertification(String certification) {
        this.certification = certification;
        return this;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public ReleaseDate withIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ReleaseDate withNote(String note) {
        this.note = note;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ReleaseDate withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ReleaseDate withType(Integer type) {
        this.type = type;
        return this;
    }

}
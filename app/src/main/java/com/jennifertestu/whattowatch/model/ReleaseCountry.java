package com.jennifertestu.whattowatch.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReleaseCountry {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("release_dates")
    @Expose
    private List<ReleaseDate> releaseDates = null;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public ReleaseCountry withIso31661(String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    public List<ReleaseDate> getReleaseDates() {
        return releaseDates;
    }

    public void setReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
    }

    public ReleaseCountry withReleaseDates(List<ReleaseDate> releaseDates) {
        this.releaseDates = releaseDates;
        return this;
    }

}
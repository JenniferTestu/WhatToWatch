package com.jennifertestu.whattowatch.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupeOffres implements Serializable
{

    @SerializedName("jw_entity_id")
    @Expose
    private String jwEntityId;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("full_path")
    @Expose
    private String fullPath;
    @SerializedName("full_paths")
    @Expose
    private FullPaths fullPaths;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("original_release_year")
    @Expose
    private int originalReleaseYear;
    @SerializedName("tmdb_popularity")
    @Expose
    private double tmdbPopularity;
    @SerializedName("object_type")
    @Expose
    private String objectType;
    @SerializedName("localized_release_date")
    @Expose
    private String localizedReleaseDate;
    @SerializedName("offers")
    @Expose
    private List<Offre> offres = new ArrayList<Offre>();
    @SerializedName("external_ids")
    @Expose
    private List<ExternalID> external_ids = null;
    @SerializedName("cinema_release_date")
    @Expose
    private String cinemaReleaseDate;
    @SerializedName("age_certification")
    @Expose
    private String ageCertification;

    public String getJwEntityId() {
        return jwEntityId;
    }

    public void setJwEntityId(String jwEntityId) {
        this.jwEntityId = jwEntityId;
    }

    public GroupeOffres withJwEntityId(String jwEntityId) {
        this.jwEntityId = jwEntityId;
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupeOffres withId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GroupeOffres withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public GroupeOffres withFullPath(String fullPath) {
        this.fullPath = fullPath;
        return this;
    }

    public FullPaths getFullPaths() {
        return fullPaths;
    }

    public void setFullPaths(FullPaths fullPaths) {
        this.fullPaths = fullPaths;
    }

    public GroupeOffres withFullPaths(FullPaths fullPaths) {
        this.fullPaths = fullPaths;
        return this;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public GroupeOffres withPoster(String poster) {
        this.poster = poster;
        return this;
    }

    public int getOriginalReleaseYear() {
        return originalReleaseYear;
    }

    public void setOriginalReleaseYear(int originalReleaseYear) {
        this.originalReleaseYear = originalReleaseYear;
    }

    public GroupeOffres withOriginalReleaseYear(int originalReleaseYear) {
        this.originalReleaseYear = originalReleaseYear;
        return this;
    }

    public double getTmdbPopularity() {
        return tmdbPopularity;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public GroupeOffres withTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
        return this;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public GroupeOffres withObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    public String getLocalizedReleaseDate() {
        return localizedReleaseDate;
    }

    public void setLocalizedReleaseDate(String localizedReleaseDate) {
        this.localizedReleaseDate = localizedReleaseDate;
    }

    public GroupeOffres withLocalizedReleaseDate(String localizedReleaseDate) {
        this.localizedReleaseDate = localizedReleaseDate;
        return this;
    }

    public List<Offre> getOffres() {
        return offres;
    }

    public void setOffres(List<Offre> offres) {
        this.offres = offres;
    }

    public GroupeOffres withOffers(List<Offre> offres) {
        this.offres = offres;
        return this;
    }

    public List<ExternalID> getExternalIDs() {
        return external_ids;
    }

    public void setExternalIDs(List<ExternalID> external_ids) {
        this.external_ids = external_ids;
    }

    public GroupeOffres withExternalIDs(List<ExternalID> external_ids) {
        this.external_ids = external_ids;
        return this;
    }

    public String getCinemaReleaseDate() {
        return cinemaReleaseDate;
    }

    public void setCinemaReleaseDate(String cinemaReleaseDate) {
        this.cinemaReleaseDate = cinemaReleaseDate;
    }

    public GroupeOffres withCinemaReleaseDate(String cinemaReleaseDate) {
        this.cinemaReleaseDate = cinemaReleaseDate;
        return this;
    }

    public String getAgeCertification() {
        return ageCertification;
    }

    public void setAgeCertification(String ageCertification) {
        this.ageCertification = ageCertification;
    }
}


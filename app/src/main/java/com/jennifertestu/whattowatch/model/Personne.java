package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Personne {

    @SerializedName("jw_entity_id")
    @Expose
    private String jwEntityId;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("object_type")
    @Expose
    private String objectType;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;

    public String getJwEntityId() {
        return jwEntityId;
    }

    public void setJwEntityId(String jwEntityId) {
        this.jwEntityId = jwEntityId;
    }

    public Personne withJwEntityId(String jwEntityId) {
        this.jwEntityId = jwEntityId;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Personne withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Personne withObjectType(String objectType) {
        this.objectType = objectType;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Personne withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Personne withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    @Override
    public String toString() {
        return fullName;
    }
}
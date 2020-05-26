package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Createur {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("credit_id")
    @Expose
    private String creditId;
    @SerializedName("name")
    @Expose
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Createur withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public Createur withCreditId(String creditId) {
        this.creditId = creditId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Createur withName(String name) {
        this.name = name;
        return this;
    }


}
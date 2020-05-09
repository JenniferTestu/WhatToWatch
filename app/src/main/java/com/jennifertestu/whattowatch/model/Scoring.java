package com.jennifertestu.whattowatch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Scoring implements Serializable
{

    @SerializedName("provider_type")
    @Expose
    private String providerType;
    @SerializedName("value")
    @Expose
    private Double value;
    //private final static long serialVersionUID = 8944378876630550085L;


    public Scoring(String providerType, Double value) {
        this.providerType = providerType;
        this.value = value;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public Scoring withProviderType(String providerType) {
        this.providerType = providerType;
        return this;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Scoring withValue(Double value) {
        this.value = value;
        return this;
    }

}

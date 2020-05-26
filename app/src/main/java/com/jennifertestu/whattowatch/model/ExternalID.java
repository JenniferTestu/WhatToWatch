package com.jennifertestu.whattowatch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExternalID implements Serializable
{

    @SerializedName("provider")
    @Expose
    private String provider;
    @SerializedName("external_id")
    @Expose
    private String external_id;
    //private final static long serialVersionUID = 8944378876630550085L;


    public ExternalID(String provider, String external_id) {
        this.provider = provider;
        this.external_id = external_id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public ExternalID withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public String getId() {
        return external_id;
    }

    public void setId(String external_id) {
        this.external_id = external_id;
    }

    public ExternalID withId(String external_id) {
        this.external_id = external_id;
        return this;
    }

}

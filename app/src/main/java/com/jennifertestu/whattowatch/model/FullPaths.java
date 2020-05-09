package com.jennifertestu.whattowatch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullPaths implements Serializable
{

    @SerializedName("MOVIE_DETAIL_OVERVIEW")
    @Expose
    private String mOVIEDETAILOVERVIEW;
    private final static long serialVersionUID = 3765434086442840628L;

    public String getMOVIEDETAILOVERVIEW() {
        return mOVIEDETAILOVERVIEW;
    }

    public void setMOVIEDETAILOVERVIEW(String mOVIEDETAILOVERVIEW) {
        this.mOVIEDETAILOVERVIEW = mOVIEDETAILOVERVIEW;
    }

    public FullPaths withMOVIEDETAILOVERVIEW(String mOVIEDETAILOVERVIEW) {
        this.mOVIEDETAILOVERVIEW = mOVIEDETAILOVERVIEW;
        return this;
    }

}


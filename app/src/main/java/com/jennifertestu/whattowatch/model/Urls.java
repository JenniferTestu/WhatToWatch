package com.jennifertestu.whattowatch.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Urls implements Serializable
{

    @SerializedName("standard_web")
    @Expose
    private String standardWeb;
    @SerializedName("deeplink_android_tv")
    @Expose
    private String deeplinkAndroidTv;
    //private final static long serialVersionUID = 7271921227681567452L;

    public String getStandardWeb() {
        return standardWeb;
    }

    public void setStandardWeb(String standardWeb) {
        this.standardWeb = standardWeb;
    }

    public Urls withStandardWeb(String standardWeb) {
        this.standardWeb = standardWeb;
        return this;
    }

    public String getDeeplinkAndroidTv() {
        return deeplinkAndroidTv;
    }

    public void setDeeplinkAndroidTv(String deeplinkAndroidTv) {
        this.deeplinkAndroidTv = deeplinkAndroidTv;
    }

    public Urls withDeeplinkAndroidTv(String deeplinkAndroidTv) {
        this.deeplinkAndroidTv = deeplinkAndroidTv;
        return this;
    }

}

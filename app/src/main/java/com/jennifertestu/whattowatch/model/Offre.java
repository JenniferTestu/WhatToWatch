package com.jennifertestu.whattowatch.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Offre implements Serializable
{

    @SerializedName("monetization_type")
    @Expose
    private String monetizationType;
    @SerializedName("provider_id")
    @Expose
    private int providerId;
    @SerializedName("retail_price")
    @Expose
    private double retailPrice;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("urls")
    @Expose
    private Urls urls;
    @SerializedName("presentation_type")
    @Expose
    private String presentationType;
    @SerializedName("date_provider_id")
    @Expose
    private String dateProviderId;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("last_change_retail_price")
    @Expose
    private double lastChangeRetailPrice;
    @SerializedName("last_change_difference")
    @Expose
    private double lastChangeDifference;
    @SerializedName("last_change_percent")
    @Expose
    private double lastChangePercent;
    @SerializedName("last_change_date")
    @Expose
    private String lastChangeDate;
    @SerializedName("last_change_date_provider_id")
    @Expose
    private String lastChangeDateProviderId;
    @SerializedName("subtitle_languages")
    @Expose
    private List<String> subtitleLanguages = null;
    @SerializedName("audio_languages")
    @Expose
    private List<String> audioLanguages = null;

    //private final static long serialVersionUID = -6176813206486684525L;

    public String getMonetizationType() {
        return monetizationType;
    }

    public void setMonetizationType(String monetizationType) {
        this.monetizationType = monetizationType;
    }

    public Offre withMonetizationType(String monetizationType) {
        this.monetizationType = monetizationType;
        return this;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public Offre withProviderId(int providerId) {
        this.providerId = providerId;
        return this;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Offre withRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Offre withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Offre withUrls(Urls urls) {
        this.urls = urls;
        return this;
    }

    public String getPresentationType() {
        return presentationType;
    }

    public void setPresentationType(String presentationType) {
        this.presentationType = presentationType;
    }

    public Offre withPresentationType(String presentationType) {
        this.presentationType = presentationType;
        return this;
    }

    public String getDateProviderId() {
        return dateProviderId;
    }

    public void setDateProviderId(String dateProviderId) {
        this.dateProviderId = dateProviderId;
    }

    public Offre withDateProviderId(String dateProviderId) {
        this.dateProviderId = dateProviderId;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Offre withDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public double getLastChangeRetailPrice() {
        return lastChangeRetailPrice;
    }

    public void setLastChangeRetailPrice(double lastChangeRetailPrice) {
        this.lastChangeRetailPrice = lastChangeRetailPrice;
    }

    public Offre withLastChangeRetailPrice(double lastChangeRetailPrice) {
        this.lastChangeRetailPrice = lastChangeRetailPrice;
        return this;
    }

    public double getLastChangeDifference() {
        return lastChangeDifference;
    }

    public void setLastChangeDifference(double lastChangeDifference) {
        this.lastChangeDifference = lastChangeDifference;
    }

    public Offre withLastChangeDifference(double lastChangeDifference) {
        this.lastChangeDifference = lastChangeDifference;
        return this;
    }

    public double getLastChangePercent() {
        return lastChangePercent;
    }

    public void setLastChangePercent(double lastChangePercent) {
        this.lastChangePercent = lastChangePercent;
    }

    public Offre withLastChangePercent(double lastChangePercent) {
        this.lastChangePercent = lastChangePercent;
        return this;
    }

    public String getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(String lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public Offre withLastChangeDate(String lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
        return this;
    }

    public String getLastChangeDateProviderId() {
        return lastChangeDateProviderId;
    }

    public void setLastChangeDateProviderId(String lastChangeDateProviderId) {
        this.lastChangeDateProviderId = lastChangeDateProviderId;
    }

    public Offre withLastChangeDateProviderId(String lastChangeDateProviderId) {
        this.lastChangeDateProviderId = lastChangeDateProviderId;
        return this;
    }

    public List<String> getSubtitleLanguages() {
        return subtitleLanguages;
    }

    public void setSubtitleLanguages(List<String> subtitleLanguages) {
        this.subtitleLanguages = subtitleLanguages;
    }

    public Offre withSubtitleLanguages(List<String> subtitleLanguages) {
        this.subtitleLanguages = subtitleLanguages;
        return this;
    }

    public List<String> getAudioLanguages() {
        return audioLanguages;
    }

    public void setAudioLanguages(List<String> audioLanguages) {
        this.audioLanguages = audioLanguages;
    }

    public Offre withAudioLanguages(List<String> audioLanguages) {
        this.audioLanguages = audioLanguages;
        return this;
    }

}


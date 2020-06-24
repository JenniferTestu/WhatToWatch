package com.jennifertestu.whattowatch.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Recherche implements Serializable {

    private ArrayList<String> age_certifications = new ArrayList<>();
    private ArrayList<String> content_types = new ArrayList<>();
    private ArrayList<String> genres = new ArrayList<>();
    private Double min_price;
    private Double max_price;
    private ArrayList<String> monetization_types = new ArrayList<>();
    private ArrayList<String> providers = new ArrayList<>();
    private int release_year_from;
    private int release_year_until;
    private int min_runtime;
    private int max_runtime;
    private int page = 0;
    private int page_size;
    private int person_id;

    public Recherche() {
    }

    public ArrayList<String> getAge_certifications() {
        return age_certifications;
    }

    public void setAge_certifications(ArrayList<String> age_certifications) {
        this.age_certifications = age_certifications;
    }

    public ArrayList<String> getContent_types() {
        return content_types;
    }

    public void setContent_types(ArrayList<String> content_types) {
        this.content_types = content_types;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public ArrayList<String> getMonetization_types() {
        return monetization_types;
    }

    public void setMonetization_types(ArrayList<String> monetization_types) {
        this.monetization_types = monetization_types;
    }

    public ArrayList<String> getProviders() {
        return providers;
    }

    public void setProviders(ArrayList<String> providers) {
        this.providers = providers;
    }

    public int getRelease_year_from() {
        return release_year_from;
    }

    public void setRelease_year_from(int release_year_from) {
        this.release_year_from = release_year_from;
    }

    public int getRelease_year_until() {
        return release_year_until;
    }

    public void setRelease_year_until(int release_year_until) {
        this.release_year_until = release_year_until;
    }

    public int getMin_runtime() {
        return min_runtime;
    }

    public void setMin_runtime(int min_runtime) {
        this.min_runtime = min_runtime;
    }

    public int getMax_runtime() {
        return max_runtime;
    }

    public void setMax_runtime(int max_runtime) {
        this.max_runtime = max_runtime;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void pageIncrementation(){
        ++page;
    }
}

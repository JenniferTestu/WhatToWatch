package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class FilmsResultats implements Serializable {

    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("results")
    private ArrayList<Film> filmsResultats;

    public FilmsResultats(int page, int totalResults, int totalPages, ArrayList<Film> movieResult) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.filmsResultats = movieResult;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<Film> getMovieResult() {
        return filmsResultats;
    }

    public void setMovieResult(ArrayList<Film> movieResult) {
        this.filmsResultats = movieResult;
    }

}

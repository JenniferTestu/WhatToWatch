package com.jennifertestu.whattowatch.model;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OffresResultats implements Serializable
{

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("page_size")
    @Expose
    private int pageSize;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("items")
    @Expose
    private List<GroupeOffres> groupeOffres = null;
    //private final static long serialVersionUID = 1285999612863457714L;


    public OffresResultats(int page, int pageSize, int totalPages, int totalResults, ArrayList<GroupeOffres> groupeOffres) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.groupeOffres = groupeOffres;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public OffresResultats withPage(int page) {
        this.page = page;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public OffresResultats withPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public OffresResultats withTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public OffresResultats withTotalResults(int totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    public List<GroupeOffres> getGroupeOffres() {
        return groupeOffres;
    }

    public void setGroupeOffres(List<GroupeOffres> groupeOffres) {
        this.groupeOffres = groupeOffres;
    }

    public OffresResultats withItems(List<GroupeOffres> groupeOffres) {
        this.groupeOffres = groupeOffres;
        return this;
    }
}


package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Genre {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String nom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Genre withId(Integer id) {
        this.id = id;
        return this;
    }
/*
    public String getNom() {

        switch (id){
            case 28:
                return ("Action");
            case 12:
                return ("Aventure");
            case 16:
                return ("Animation");
            case 35:
                return ("Comédie");
            case 80:
                return ("Crime");
            case 99:
                return ("Documentaire");
            case 18:
                return ("Drame");
            case 10751:
                return ("Familial");
            case 14:
                return ("Fantastique");
            case 36:
                return ("Histoire");
            case 27:
                return ("Horeur");
            case 10402:
                return ("Musique");
            case 9648:
                return ("Mystère");
            case 10749:
                return ("Romance");
            case 878:
                return ("Science-Fiction");
            case 10770:
                return ("Téléfilm");
            case 53:
                return ("Thriller");
            case 10752:
                return ("Guerre");
            case 37:
                return ("Western");
            default:
                return "Non classé";

        }
    }
*/
}
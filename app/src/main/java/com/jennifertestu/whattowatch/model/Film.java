package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Film implements Serializable {

    // Id
    @SerializedName("id")
    private int id;
    // Id IMDB
    //@SerializedName("imdb_id")
    private String idImdb;
    // Titre du film
    @SerializedName("title")
    private String titre;
    // Affiche du film
    @SerializedName("poster_path")
    private String urlAffiche;
    // Date de sortie
    @SerializedName("release_date")
    @Expose
    private String date;
    // Catégories du films
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    // Longue description
    @SerializedName("overview")
    private String longueDesc;
    // Liste des personnes ayant travaillé pour ce film
    private Credits credits = null;
    // Liste des plateformes
    private List<Offre> listeOffres;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdImdb() {
        return idImdb;
    }

    public void setIdImdb(String idImdb) {
        this.idImdb = idImdb;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getUrlAffiche() {
        return urlAffiche;
    }

    public void setUrlAffiche(String urlAffiche) {
        this.urlAffiche = urlAffiche;
    }

    public String getLongueDesc() {
        return longueDesc;
    }

    public void setLongueDesc(String longueDesc) {
        this.longueDesc = longueDesc;
    }

    public Credits getCredits() {
        return credits;
    }

    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    public List<Offre> getListeOffres() {
        return listeOffres;
    }

    public void setListeOffres(List<Offre> listeOffres) {
        this.listeOffres = listeOffres;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRealisateurs(){

        String result = "";
        for(int i=0;i<credits.getCrew().size();i++) {
            if(credits.getCrew().get(i).getJob().equals("Director")){
                result = result + credits.getCrew().get(i).getName();
            }
        }
        return result;
    }
}

package com.jennifertestu.whattowatch.model;

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
    // Réalisateur
    //@SerializedName("userId")
    private Personne realisateur;
    // Affiche du film
    @SerializedName("poster_path")
    private String urlAffiche;
    // Année de sortie
    //private int annee;
    // Catégories du films

    // Longue description
    @SerializedName("overview")
    private String longueDesc;
    // Liste des acteurs
    private List<Personne> casting;
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

    public Personne getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(Personne realisateur) {
        this.realisateur = realisateur;
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

    public List<Personne> getCasting() {
        return casting;
    }

    public void setCasting(List<Personne> casting) {
        this.casting = casting;
    }

    public List<Offre> getListeOffres() {
        return listeOffres;
    }

    public void setListeOffres(List<Offre> listeOffres) {
        this.listeOffres = listeOffres;
    }
}

package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
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
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genres = null;
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

    public List<String> getGenres() {
        List<String> result = new ArrayList<>();

        for (Integer g:genres) {

            if(g==28) {
                result.add("Action");
            }else if(g==12) {
                result.add("Aventure");
            }else if(g==16) {
                result.add("Animation");
            }else if(g==35) {
                result.add("Comédie");
            }else if(g==80) {
                result.add("Crime");
            }else if(g==99) {
                result.add("Documentaire");
            }else if(g==18) {
                result.add("Drame");
            }else if(g==10751) {
                result.add("Familial");
            }else if(g==14) {
                result.add("Fantastique");
            }else if(g==36) {
                result.add("Histoire");
            }else if(g==27) {
                result.add("Horeur");
            }else if(g==10402) {
                result.add("Musique");
            }else if(g==9648) {
                result.add("Mystère");
            }else if(g==10749) {
                result.add("Romance");
            }else if(g==878) {
                result.add("Science-Fiction");
            }else if(g==10770) {
                result.add("Téléfilm");
            }else if(g==53) {
                result.add("Thriller");
            }else if(g==10752) {
                result.add("Guerre");
            }else if(g==37){
                    result.add ("Western");
            }
        }

        return result;
    }

    public void setGenres(List<Integer> genres) {
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

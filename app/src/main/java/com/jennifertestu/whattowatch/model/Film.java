package com.jennifertestu.whattowatch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Film implements Serializable {

    // Id TMDB
    @SerializedName("id")
    private int id;
    // Id IMDB
    //@SerializedName("imdb_id")
    private String idImdb;
    // Id JW
    private int idJw;
    // Id dans la BDD Firebase
    private String idFirebase;
    // Type JW film ou serie
    private String type;
    // Titre du film
    @SerializedName("title")
    private String titre;
    // Nom de la serie
    @SerializedName("name")
    private String nomSerie;
    // Affiche du film
    @SerializedName("poster_path")
    private String urlAffiche;
    // Image d'arrière plan
    @SerializedName("backdrop_path")
    private String urlArrierePlan;
    // Date de sortie du film
    @SerializedName("release_date")
    @Expose
    private String date;
    // Date de sortie de la série
    @SerializedName("first_air_date")
    @Expose
    private String date_premier_ep;
    // Ids des catégories du films
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genresIds = null;
    // Categories du films
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    // Longue description
    @SerializedName("overview")
    private String longueDesc;
    // Liste des personnes ayant travaillé pour ce film
    @SerializedName("credits")
    @Expose
    private Credits credits;
    // Liste des plateformes
    private List<Offre> listeOffres = new ArrayList<Offre>();
    // Liste des videos
    @SerializedName("videos")
    @Expose
    private VideosResultats listeVideos;
    // Nombre de saisons
    @SerializedName("number_of_seasons")
    @Expose
    private int season_number;
    // Durée du film
    @SerializedName("runtime")
    @Expose
    private int runtime;
    // Durée moyenne d'un episode
    @SerializedName("episode_run_time")
    @Expose
    private ArrayList<Integer> episode_run_time;
    @SerializedName("created_by")
    @Expose
    private ArrayList<Createur> created_by;
    private String age;

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

    public String getUrlArrierePlan() {
        return urlArrierePlan;
    }

    public void setUrlArrierePlan(String urlArrierePlan) {
        this.urlArrierePlan = urlArrierePlan;
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

    public List<String> getGenresIds() {
        List<String> result = new ArrayList<>();

        for (Integer g: genresIds) {

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

    public void setGenresIds(List<Integer> genresIds) {
        this.genresIds = genresIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRealisateurs(){

        String result = credits.getCrew().get(0).getName();
        for(int i=1;i<credits.getCrew().size();i++) {
            if(credits.getCrew().get(i).getJob().equals("Director")){
                result = result + ", " + credits.getCrew().get(i).getName();
            }
        }
        return result;
    }

    public VideosResultats getListeVideos() {
        return listeVideos;
    }

    public void setListeVideos(VideosResultats listeVideos) {
        this.listeVideos = listeVideos;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getDate_premier_ep() {
        return date_premier_ep;
    }

    public void setDate_premier_ep(String date_premier_ep) {
        this.date_premier_ep = date_premier_ep;
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }

    public int getEpisode_run_time_average() {
        if (episode_run_time.size()==0 || episode_run_time==null) return 0;
        int count = 0;
        for (Integer i:episode_run_time) {
            count += i;
        }
        return count/episode_run_time.size();
    }

    public void setEpisode_run_time(ArrayList<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public String getCreated_by() {
        String result = created_by.get(0).getName();
        for(int i=1;i<created_by.size();i++) {
                result = result + ", " + created_by.get(i).getName();
        }
        return result;
    }

    public void setCreated_by(ArrayList<Createur> created_by) {
        this.created_by = created_by;
    }

    public int getIdJw() {
        return idJw;
    }

    public void setIdJw(int idJw) {
        this.idJw = idJw;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdFirebase() {
        return idFirebase;
    }

    public void setIdFirebase(String idFirebase) {
        this.idFirebase = idFirebase;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Film)) {
            return false;
        }
        Film other = (Film) o;
        return id == other.id;
    }
/*
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
*/
}

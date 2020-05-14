package com.jennifertestu.whattowatch.model;

import com.jennifertestu.whattowatch.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum Plateforme implements Serializable {

    ITUNES("Apple iTunes", 2, R.drawable.itunes,13),
    PLAY("Google Play Movies", 3, R.drawable.googleplay,14),
    NETFLIX("Netflix", 8, R.drawable.netflix,1),
    MUBI("Mubi", 11, R.drawable.mubi,23),
    RAKU("Rakuten TV", 35, R.drawable.raku,22),
    OCSGO("OCS Go", 56, R.drawable.ocsgo,4),
    CANALPLAYVOD("Canal+ VOD", 58, R.drawable.canalvod,19),
    BBOX("Bbox VOD", 59, R.drawable.bbox,20),
    ORANGE("Orange VOD", 61, R.drawable.orange,9),
    MICROSOFT("Microsoft Store", 68, R.drawable.microsoft,15),
    AMAZONPRIMEVIDEO("Amazon Prime Video", 119, R.drawable.amazonprime,5),
    FILMOTV("Filmo TV", 138, R.drawable.filmotv,18),
    MYTF1VOD("MyTF1vod", 145, R.drawable.mytf1vod,10),
    SIXPLAY("Sixplay", 147, R.drawable.sixplay,8),
    PANTAFLIX("Pantaflix", 177, R.drawable.pantaflix,21),
    YOUTUBERED("Youtube Premium", 188, R.drawable.youtubered,17),
    YOUTUBE("YouTube", 192, R.drawable.youtube,16),
    SFRPLAY("SFR Play", 193, R.drawable.sfrplay,7),
    ARTE("Arte", 234, R.drawable.arte,12),
    FRANCETV("France TV", 236, R.drawable.francetv,11),
    CRUNCHYROLL("Crunchyroll", 283, R.drawable.crunchyroll,25),
    LACINETEK("La Cinetek", 310, R.drawable.lacinetek,24),
    CINEMASALADEMANDE("Cinemas à la Demande", 324, R.drawable.cinemasalademande,26),
    DISNEYPLUS("Disney Plus", 337, R.drawable.disneyplus,6),
    CANALSERIES("Canal+ Séries", 345, R.drawable.canalseries,3),
    APPLETVPLUS("Apple TV Plus", 350, R.drawable.appletvplus,28),
    WAKANIM("Wakanim", 354, R.drawable.wakanim,27),
    CANALPLUS("Canal+", 381, R.drawable.canalplus,2),
    CANALPLUSDISNEYPLUS("Canal+ Disney+", 382, R.drawable.canalplusdisneyplus,29);

    private final String nom;
    private final int id;
    private final int image;
    private final int place;

    private static final Map<Integer, Plateforme> byId = new HashMap<Integer, Plateforme>();
    static {
        for (Plateforme e : Plateforme.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    Plateforme(String nom, int id, int image, int place) {
        this.nom = nom;
        this.id = id;
        this.image = image;
        this.place = place;
    }

    public static Plateforme getById(int id) {
        /*
        for(Plateforme e : values()) {
            if(e.id == id){
                return e;
            }else{
                Log.e("ID non trouvé", String.valueOf(id));
                return null;
            }
        }
        throw new IllegalArgumentException();
         */
        return byId.get(id);
    }

    public String getNom(){ return this.nom; }

    public int getId() {
        return this.id;
    }

    public int getImage() {
        return this.image;
    }

    public int getPlace() {
        return place;
    }
}

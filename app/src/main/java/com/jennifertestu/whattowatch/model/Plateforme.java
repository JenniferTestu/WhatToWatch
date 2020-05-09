package com.jennifertestu.whattowatch.model;

import com.jennifertestu.whattowatch.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum Plateforme implements Serializable {

    ITUNES("Apple iTunes", 2, R.drawable.itunes),
    PLAY("Google Play Movies", 3, R.drawable.googleplay),
    NETFLIX("Netflix", 8, R.drawable.netflix),
    MUBI("Mubi", 11, R.drawable.mubi),
    RAKU("Rakuten TV", 35, R.drawable.raku),
    OCSGO("OCS Go", 56, R.drawable.ocsgo),
    CANALPLAYVOD("Canal+ VOD", 58, R.drawable.canalvod),
    BBOX("Bbox VOD", 59, R.drawable.bbox),
    ORANGE("Orange VOD", 61, R.drawable.orange),
    MICROSOFT("Microsoft Store", 68, R.drawable.microsoft),
    AMAZONPRIMEVIDEO("Amazon Prime Video", 119, R.drawable.amazonprime),
    FILMOTV("Filmo TV", 138, R.drawable.filmotv),
    MYTF1VOD("MyTF1vod", 145, R.drawable.mytf1vod),
    SIXPLAY("Sixplay", 147, R.drawable.sixplay),
    PANTAFLIX("Pantaflix", 177, R.drawable.pantaflix),
    YOUTUBERED("Youtube Premium", 188, R.drawable.youtubered),
    YOUTUBE("YouTube", 192, R.drawable.youtube),
    SFRPLAY("SFR Play", 193, R.drawable.sfrplay),
    ARTE("Arte", 234, R.drawable.arte),
    FRANCETV("France TV", 236, R.drawable.francetv),
    CRUNCHYROLL("Crunchyroll", 283, R.drawable.crunchyroll),
    LACINETEK("La Cinetek", 310, R.drawable.lacinetek),
    CINEMASALADEMANDE("Cinemas à la Demande", 324, R.drawable.cinemasalademande),
    DISNEYPLUS("Disney Plus", 337, R.drawable.disneyplus),
    CANALSERIES("Canal+ Séries", 345, R.drawable.canalseries),
    APPLETVPLUS("Apple TV Plus", 350, R.drawable.appletvplus),
    WAKANIM("Wakanim", 354, R.drawable.wakanim),
    CANALPLUS("Canal+", 381, R.drawable.canalplus),
    CANALPLUSDISNEYPLUS("Canal+ Disney+", 382, R.drawable.canalplusdisneyplus); // <- NB: le point-virgule pour mettre fin à la liste des constantes !

    private final String nom;
    private final int id;
    private final int image;

    private static final Map<Integer, Plateforme> byId = new HashMap<Integer, Plateforme>();
    static {
        for (Plateforme e : Plateforme.values()) {
            if (byId.put(e.getId(), e) != null) {
                throw new IllegalArgumentException("duplicate id: " + e.getId());
            }
        }
    }

    Plateforme(String nom, int id, int image) {
        this.nom = nom;
        this.id = id;
        this.image = image;
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

}

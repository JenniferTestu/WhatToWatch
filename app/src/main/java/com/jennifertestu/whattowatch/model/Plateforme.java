package com.jennifertestu.whattowatch.model;

import com.jennifertestu.whattowatch.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum Plateforme implements Serializable {

    ITUNES("Apple iTunes", "itu",2, R.drawable.itunes,13),
    PLAY("Google Play Movies", "ply",3, R.drawable.googleplay,14),
    NETFLIX("Netflix", "nfx",8, R.drawable.netflix,1),
    MUBI("Mubi", "mbi",11, R.drawable.mubi,23),
    RAKU("Rakuten TV", "wki",35, R.drawable.raku,22),
    OCSGO("OCS Go", "ocs",56, R.drawable.ocsgo,4),
    CANALPLAYVOD("Canal+ VOD", "cpv",58, R.drawable.canalvod,19),
    BBOX("Bbox VOD", "box",59, R.drawable.bbox,20),
    ORANGE("Orange VOD", "org",61, R.drawable.orange,9),
    MICROSOFT("Microsoft Store", "msf",68, R.drawable.microsoft,15),
    AMAZONPRIMEVIDEO("Amazon Prime Video", "prv",119, R.drawable.amazonprime,5),
    FILMOTV("Filmo TV", "flt",138, R.drawable.filmotv,18),
    MYTF1VOD("MyTF1vod", "mtf",145, R.drawable.mytf1vod,10),
    SIXPLAY("Sixplay", "sxp",147, R.drawable.sixplay,8),
    PANTAFLIX("Pantaflix", "pfx",177, R.drawable.pantaflix,21),
    YOUTUBERED("Youtube Premium", "ytr",188, R.drawable.youtubered,17),
    YOUTUBE("YouTube", "yot",192, R.drawable.youtube,16),
    SFRPLAY("SFR Play", "sfr",193, R.drawable.sfrplay,7),
    ARTE("Arte", "art",234, R.drawable.arte,12),
    FRANCETV("France TV", "ftv",236, R.drawable.francetv,11),
    CRUNCHYROLL("Crunchyroll", "cru",283, R.drawable.crunchyroll,25),
    LACINETEK("La Cinetek", "lct",310, R.drawable.lacinetek,24),
    CINEMASALADEMANDE("Cinemas à la Demande", "cad",324, R.drawable.cinemasalademande,26),
    DISNEYPLUS("Disney Plus", "dnp",337, R.drawable.disneyplus,6),
    CANALSERIES("Canal+ Séries", "cns",345, R.drawable.canalseries,3),
    APPLETVPLUS("Apple TV Plus", "atp",350, R.drawable.appletvplus,28),
    WAKANIM("Wakanim", "wak",354, R.drawable.wakanim,27),
    CANALPLUS("Canal+", "cpd",381, R.drawable.canalplus,2),
    CANALPLUSDISNEYPLUS("Canal+ Disney+", "cdp",382, R.drawable.canalplusdisneyplus,29);

    private final String nom;
    private final String code;
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

    Plateforme(String nom, String code, int id, int image, int place) {
        this.nom = nom;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public int getId() {
        return this.id;
    }

    public int getImage() {
        return this.image;
    }

    public int getPlace() {
        return this.place;
    }
}

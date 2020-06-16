package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.jennifertestu.whattowatch.BuildConfig;
import com.jennifertestu.whattowatch.adapter.FilmAdapter;
import com.jennifertestu.whattowatch.communication.ConnexionAPI;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Credits;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.GroupeOffres;
import com.jennifertestu.whattowatch.model.OffresResultats;
import com.jennifertestu.whattowatch.model.Recherche;
import com.jennifertestu.whattowatch.model.ExternalID;
import com.jennifertestu.whattowatch.model.VideosResultats;
import com.jennifertestu.whattowatch.utils.ActionsMenu;
import com.jennifertestu.whattowatch.utils.BlurImage;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;
    private int BLUR_PRECENTAGE = 50;
    private final Handler uiHandler = new Handler();

    private Film filmPrecedent = null;
    private ArrayList<Film> listeFilms = new ArrayList<Film>();
    private FilmAdapter filmAdapter;
    private SwipeFlingAdapterView flingContainer;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionsMenu.menuPrincipal(this);
    

        // Deconnexion
        ImageView deco = findViewById(R.id.deco);
        deco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

        // Ecran de recherche
        ImageButton recherche = findViewById(R.id.recherche);
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RechercheActivity.class);
                startActivity(i);
            }
        });

        // Bouton précédent
        final ImageButton bouton_precedent = findViewById(R.id.bouton_precedent);
        bouton_precedent.setClickable(false);
        bouton_precedent.setColorFilter(Color.GRAY);
        bouton_precedent.setAlpha(.1f);
        bouton_precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bouton_precedent.setColorFilter(Color.GRAY);
                bouton_precedent.setAlpha(.2f);
                bouton_precedent.setClickable(false);

                listeFilms.add(0, filmPrecedent);
                filmAdapter.notifyDataSetChanged();
                filmPrecedent = null;
                flingContainer.removeAllViewsInLayout();
                flingContainer.forceLayout();

                changerBackground();

            }
        });

        ImageButton bouton_wishlist = findViewById(R.id.bouton_wishlist);
        bouton_wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WishlistActivity.class);
                startActivity(intent);
            }
        });


        flingContainer = (SwipeFlingAdapterView)findViewById(R.id.frame);
/*
        ConnexionAPI.getInstance()
                .getMovieApi()
                .getFilmWithID(744,API_KEY,"fr-FR")
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                        Film film = response.body();

                        System.out.println(film);

                        listeFilms.add(film);
                        filmAdapter = new FilmAdapter(getApplicationContext(), R.layout.item, listeFilms );

                        flingContainer.setAdapter(filmAdapter);
                        filmAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
                        Log.e("Erreur","Oups, une erreur de requête");
                        t.printStackTrace();
                    }
                });
*/

        recupererFilms();
        filmAdapter = new FilmAdapter(getApplicationContext(), R.layout.item, listeFilms);
        flingContainer.setAdapter(filmAdapter);
        filmAdapter.notifyDataSetChanged();

        Log.e("Taille dans le Main", String.valueOf(listeFilms.size()));


        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                bouton_precedent.setClickable(true);
                bouton_precedent.clearColorFilter();
                bouton_precedent.setAlpha(1f);
                filmPrecedent = listeFilms.get(0);
                listeFilms.remove(0);
                filmAdapter.notifyDataSetChanged();
                changerBackground();

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                non();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                oui();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //listeFilms.add("XML ".concat(String.valueOf(i)));
                //filmAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                //i++;
                filmAdapter.notifyDataSetChanged();
                changerBackground();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }

        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Cliqué!",Toast.LENGTH_SHORT).show();
            }
        });

        // Bouton Non
        ImageButton bouton_non = findViewById(R.id.bouton_non);
        bouton_non.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectLeft();
                //non();
            }
        });

        // Bouton Oui
        ImageButton bouton_oui = findViewById(R.id.bouton_oui);
        bouton_oui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flingContainer.getTopCardListener().selectRight();
                //oui();
            }
        });

    }

    public void non(){
        Toast.makeText(MainActivity.this, "Nope",Toast.LENGTH_SHORT).show();
    }

    public void oui(){

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        String utilisateurId = mFirebaseAuth.getCurrentUser().getUid();
        CollectionReference collectionReference = fStore.collection("Utilisateurs").document(utilisateurId).collection("ToWatch");

        Map<String,Object> towatch = new HashMap<>();
        towatch.put("id_tmdb",filmPrecedent.getId());
        towatch.put("id_jw",filmPrecedent.getIdJw());
        towatch.put("type_jw",filmPrecedent.getType());


        collectionReference.add(towatch).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MainActivity.this, "Film ajouté", Toast.LENGTH_SHORT).show();
            }
        });

        //Toast.makeText(MainActivity.this, "Oui!",Toast.LENGTH_SHORT).show();
    }

    public void recupererFilms(){
/*
        ConnexionAPI.getInstance()
                .getMovieApi()
                .getFilmsRecommendationsWithID(744,TMDB_API_KEY,"fr-FR")
                .enqueue(new Callback<FilmsResultats>(){
                    @Override
                    public void onResponse(@NonNull Call<FilmsResultats> call, @NonNull Response<FilmsResultats> response) {
                        ArrayList<Film> resultats = response.body().getMovieResult();


                        for (final Film f:resultats) {

                            ConnexionAPI.getInstance()
                                    .getMovieApi()
                                    .getCredits(f.getId(),TMDB_API_KEY)
                                    .enqueue(new Callback<Credits>(){

                                        @Override
                                        public void onResponse(@NonNull Call<Credits> call, @NonNull Response<Credits> response) {
                                            Credits credits = response.body();
                                            f.setCredits(credits);
                                            Log.e("CREDIT",f.getCredits().getCrew().get(1).getName());

                                        }

                                        @Override
                                        public void onFailure(Call<Credits> call, Throwable t) {

                                        }
                                    });

                            ConnexionAPI.getInstance()
                                    .getMovieApi()
                                    .getVideosWithID(f.getId(),TMDB_API_KEY,"fr-FR")
                                    .enqueue(new Callback<VideosResultats>(){

                                        @Override
                                        public void onResponse(@NonNull Call<VideosResultats> call, @NonNull Response<VideosResultats> response) {
                                            VideosResultats videosResultats = response.body();
                                            f.setListeVideos(videosResultats.getResults());

                                        }

                                        @Override
                                        public void onFailure(Call<VideosResultats> call, Throwable t) {

                                        }
                                    });

                            ConnexionAPI.getInstance()
                                    .getJWApi()
                                    .getOffres("{\"age_certifications\":[],\"content_types\":[\"movie\"],\"genres\":[],\"languages\":null,\"min_price\":null,\"matching_offers_only\":null,\"max_price\":null,\"monetization_types\":[],\"presentation_types\":[],\"providers\":[],\"release_year_from\":null,\"release_year_until\":null,\"scoring_filter_types\":null,\"timeline_type\":null,\"sort_by\":null,\"sort_asc\":null,\"query\":\""+f.getTitre()+"\",\"page\":1,\"page_size\":1}")
                                    .enqueue(new Callback<OffresResultats>(){

                                        @Override
                                        public void onResponse(@NonNull Call<OffresResultats> call, @NonNull Response<OffresResultats> response) {

                                            OffresResultats offresResultats = response.body();
                                            Scoring scoring = new Scoring("tmdb:id",Double.valueOf(f.getId()));

                                            if(offresResultats.getGroupeOffres()!=null || offresResultats.getGroupeOffres().isEmpty()==false || offresResultats.getGroupeOffres().get(0).getScoring().contains(scoring)) {

                                                f.setListeOffres(offresResultats.getGroupeOffres().get(0).getOffres());
                                                listeFilms.add(f);
                                                if(listeFilms.size()==1){
                                                    changerBackground();
                                                }

                                                Log.e("Dans le IF ?", "OUI");
                                                filmAdapter.notifyDataSetChanged();

                                            }else {
                                                Log.e("ELSE","SORTIE");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<OffresResultats> call, Throwable t) {
                                            f.setListeOffres(new ArrayList<Offre>());

                                            Log.e("PROB",t.toString());
                                        }
                                    });


                        }


                        filmAdapter = new FilmAdapter(getApplicationContext(), R.layout.item, listeFilms );
                        flingContainer.setAdapter(filmAdapter);
                        filmAdapter.notifyDataSetChanged();

                    }


                    @Override
                    public void onFailure(@NonNull Call<FilmsResultats> call, @NonNull Throwable t) {
                        Log.e("Erreur","Oups, une erreur de requête");
                        t.printStackTrace();
                    }

                });

*/

        Recherche recherche;
        final SharedPreferences mPrefs = getSharedPreferences("Recherche", 0);
        // Récupérer la recherche et la convertir en objet Recherche
        Gson gson = new Gson();
        String json = mPrefs.getString("Champs", "");

        if(json.equals("")){

            Intent i = new Intent(MainActivity.this,RechercheActivity.class);
            finish();
            startActivity(i);

        }else {

            ConnexionAPI.getInstance()
                    .getJWApi()
                    .getOffres(json)
                    .enqueue(new Callback<OffresResultats>() {

                        @Override
                        public void onResponse(Call<OffresResultats> call, Response<OffresResultats> response) {
                            Log.e("Je suis", "dans les offres");

                            OffresResultats offresResultats = response.body();

                            for (final GroupeOffres go : offresResultats.getGroupeOffres()) {

                                final String type;
                                if (go.getObjectType().matches("show")){
                                    type="tv";
                                } else {
                                    type="movie";
                                }

                                ConnexionAPI.getInstance()
                                        .getJWApi()
                                        .getDetails(go.getObjectType(),go.getId())
                                        .enqueue(new Callback<GroupeOffres>() {
                                            @Override
                                            public void onResponse(Call<GroupeOffres> call, Response<GroupeOffres> response) {

                                                if(response.isSuccessful()) {

                                                    Log.e("Je suis", "dans le details");

                                                    final GroupeOffres resultats = response.body();

                                                    int tmdb_id = 0; // TMDB id

                                                    if (resultats.getExternalIDs()!=null && !resultats.getExternalIDs().isEmpty() ) {

                                                        for (ExternalID s : resultats.getExternalIDs()) {
                                                            if (s.getProvider().equals("tmdb"))
                                                                tmdb_id = Integer.parseInt(s.getId());
                                                        }


                                                        if (tmdb_id != 0) {
                                                            ConnexionAPI.getInstance()
                                                                    .getMovieApi()
                                                                    .getFilmWithID(type, tmdb_id, TMDB_API_KEY, "fr-FR")
                                                                    .enqueue(new Callback<Film>() {
                                                                        @Override
                                                                        public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                                                                            Log.e("Je suis", "dans le film with id");

                                                                            if (response.isSuccessful()) {

                                                                                final Film film = response.body();
                                                                                film.setListeOffres(resultats.getOffres());
                                                                                film.setAge(resultats.getAgeCertification());
                                                                                film.setType(resultats.getObjectType());
                                                                                //System.out.println(film);
/*
                                                                        ConnexionAPI.getInstance()
                                                                                .getMovieApi()
                                                                                .getVideosWithID(type, film.getId(), TMDB_API_KEY, "fr-FR")
                                                                                .enqueue(new Callback<VideosResultats>() {

                                                                                    @Override
                                                                                    public void onResponse(@NonNull Call<VideosResultats> call, @NonNull Response<VideosResultats> response) {
                                                                                        VideosResultats videosResultats = response.body();
                                                                                        film.setListeVideos(videosResultats.getResults());

                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Call<VideosResultats> call, Throwable t) {
                                                                                        Log.e("Erreur", "Videos du film");
                                                                                        t.printStackTrace();
                                                                                    }
                                                                                });

                                                                        ConnexionAPI.getInstance()
                                                                                .getMovieApi()
                                                                                .getCredits(type, film.getId(), TMDB_API_KEY)
                                                                                .enqueue(new Callback<Credits>() {

                                                                                    @Override
                                                                                    public void onResponse(@NonNull Call<Credits> call, @NonNull Response<Credits> response) {
                                                                                        Log.e("Je suis", "dans les credits");

                                                                                        Credits credits = response.body();
                                                                                        film.setCredits(credits);

                                                                                    }

                                                                                    @Override
                                                                                    public void onFailure(Call<Credits> call, Throwable t) {
                                                                                        Log.e("Erreur", "Credit du film");
                                                                                        t.printStackTrace();
                                                                                    }
                                                                                });
*/
                                                                                film.setIdJw(go.getId());
                                                                                listeFilms.add(film);
                                                                                filmAdapter.notifyDataSetChanged();
                                                                            }
                                                                        }

                                                                        @Override
                                                                        public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
                                                                            Log.e("Erreur", "Details du film");
                                                                            t.printStackTrace();
                                                                        }
                                                                    });
                                                        }
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<GroupeOffres> call, Throwable t) {
                                                Log.e("Erreur", "Recherche des details de films");
                                                t.printStackTrace();
                                            }

                                        });

                            }


                            //filmAdapter = new FilmAdapter(getApplicationContext(), R.layout.item, listeFilms);
                            //flingContainer.setAdapter(filmAdapter);
                            //filmAdapter.notifyDataSetChanged();
                            Log.e("Main","Taille de "+String.valueOf(listeFilms.size()));

                        }

                        @Override
                        public void onFailure(Call<OffresResultats> call, Throwable t) {
                            Log.e("Erreur", "Recherche des films");
                            t.printStackTrace();
                        }
                    });

        }


    }

    public void changerBackground() {

        if (listeFilms.size()>0) {
            final ImageView background = findViewById(R.id.background);

            //Configure target for
            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    background.setImageBitmap(BlurImage.fastblur(bitmap, 1f, BLUR_PRECENTAGE));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                    //background.setImageResource(R.mipmap.ic_launcher);

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            };

            background.setTag(target);
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/" + "w780" + listeFilms.get(0).getUrlAffiche())
                    .into(target);
        }
    }

    public void onResume(){
        super.onResume();
        changerBackground();
    }
}


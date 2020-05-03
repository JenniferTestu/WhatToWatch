package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.jennifertestu.whattowatch.BuildConfig;
import com.jennifertestu.whattowatch.adapter.FilmAdapter;
import com.jennifertestu.whattowatch.communication.ConnexionAPI;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Credits;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.FilmsResultats;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;

    private Film filmPrecedent = null;
    private ArrayList<Film> listeFilms;
    private FilmAdapter filmAdapter;
    private SwipeFlingAdapterView flingContainer;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bouton précédent
        final ImageButton bouton_precedent = findViewById(R.id.bouton_precedent);
        bouton_precedent.setClickable(false);
        bouton_precedent.setColorFilter(Color.GRAY);
        bouton_precedent.setAlpha(.2f);
        bouton_precedent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Element precedent",filmPrecedent.getTitre());
                bouton_precedent.setColorFilter(Color.GRAY);
                bouton_precedent.setAlpha(.2f);
                bouton_precedent.setClickable(false);

                listeFilms.add(0, filmPrecedent);
                filmAdapter.notifyDataSetChanged();
                filmPrecedent = null;
                flingContainer.removeAllViewsInLayout();
                flingContainer.forceLayout();

            }
        });

        flingContainer = (SwipeFlingAdapterView)findViewById(R.id.frame);
        listeFilms = new ArrayList<Film>();
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
        ConnexionAPI.getInstance()
                .getMovieApi()
                .getFilmsRecommendationsWithID(744,TMDB_API_KEY,"fr-FR")
                .enqueue(new Callback<FilmsResultats>(){
                    @Override
                    public void onResponse(@NonNull Call<FilmsResultats> call, @NonNull Response<FilmsResultats> response) {
                        listeFilms = response.body().getMovieResult();

                        filmAdapter = new FilmAdapter(getApplicationContext(), R.layout.item, listeFilms );

                        flingContainer.setAdapter(filmAdapter);

                        for (final Film f:listeFilms) {
                            ConnexionAPI.getInstance()
                                    .getMovieApi()
                                    .getCredits(f.getId(),TMDB_API_KEY)
                                    .enqueue(new Callback<Credits>(){

                                                 @Override
                                                 public void onResponse(@NonNull Call<Credits> call, @NonNull Response<Credits> response) {
                                                     Credits credits = response.body();
                                                     f.setCredits(credits);
                                                     Log.e("CREDIT",f.getCredits().getCrew().get(1).getName());
                                                     filmAdapter.notifyDataSetChanged();

                                                 }

                                                 @Override
                                                 public void onFailure(Call<Credits> call, Throwable t) {

                                                 }
                                             });
                        }



                    }

                    @Override
                    public void onFailure(@NonNull Call<FilmsResultats> call, @NonNull Throwable t) {
                        Log.e("Erreur","Oups, une erreur de requête");
                        t.printStackTrace();
                    }
                });

        System.out.println(listeFilms.size());


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
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Nope",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Oui!",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //listeFilms.add("XML ".concat(String.valueOf(i)));
                //filmAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                //i++;
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



    }

}

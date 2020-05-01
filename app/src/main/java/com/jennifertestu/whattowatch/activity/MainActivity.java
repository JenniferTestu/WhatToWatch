package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jennifertestu.whattowatch.BuildConfig;
import com.jennifertestu.whattowatch.adapter.FilmAdapter;
import com.jennifertestu.whattowatch.communication.ConnexionAPI;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.FilmsResultats;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    // /gradle.properties à ignorer dans Git
    private static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;

    private ArrayList<String> al;
    private ArrayList<Film> listeFilms;
    private FilmAdapter filmAdapter;
    private SwipeFlingAdapterView flingContainer;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                        filmAdapter.notifyDataSetChanged();

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

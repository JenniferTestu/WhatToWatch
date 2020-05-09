package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jennifertestu.whattowatch.BuildConfig;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.adapter.FilmAdapter;
import com.jennifertestu.whattowatch.adapter.ToWatchAdapter;
import com.jennifertestu.whattowatch.communication.ConnexionAPI;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.utils.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistActivity extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private static final String TMDB_API_KEY = BuildConfig.TMDB_API_KEY;

    private RecyclerView recyclerView;
    private ToWatchAdapter recyclerAdapter;

    private ArrayList<Film> listeFilms = new ArrayList<Film>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recyclerView = findViewById(R.id.recyclerView);

        ConnexionAPI.getInstance()
                .getMovieApi()
                .getFilmWithID(744,TMDB_API_KEY,"fr-FR")
                .enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                        Film film = response.body();

                        System.out.println(film);

                        listeFilms.add(film);

                        recyclerAdapter = new ToWatchAdapter(getApplicationContext(), listeFilms);
                        recyclerView.setAdapter(recyclerAdapter);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
                        Log.e("Erreur","Oups, une erreur de requête");
                        t.printStackTrace();
                    }
                });



        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ToWatchAdapter.FilmViewHolder) {
            // get the removed item name to display it in snack bar
            String name = listeFilms.get(viewHolder.getAdapterPosition()).getTitre();

            if(direction==ItemTouchHelper.LEFT) {
                // backup of removed item for undo purpose
                final Film deletedItem = listeFilms.get(viewHolder.getAdapterPosition());
                final int deletedIndex = viewHolder.getAdapterPosition();

                // remove the item from recycler view
                recyclerAdapter.removeItem(viewHolder.getAdapterPosition());

                Log.e("MESSAGE",name+" supprimé");

            }else if(direction==ItemTouchHelper.RIGHT){

                recyclerAdapter.removeItem(viewHolder.getAdapterPosition());

                Log.e("MESSAGE",name+" a été vu !");

            }
        }
    }

}

package com.jennifertestu.whattowatch.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jennifertestu.whattowatch.BuildConfig;
import com.jennifertestu.whattowatch.R;
import com.jennifertestu.whattowatch.adapter.FilmAdapter;
import com.jennifertestu.whattowatch.adapter.ToWatchAdapter;
import com.jennifertestu.whattowatch.communication.ConnexionAPI;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.GroupeOffres;
import com.jennifertestu.whattowatch.utils.RecyclerItemTouchHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();

        String utilisateurId = mFirebaseAuth.getCurrentUser().getUid();
        CollectionReference collectionReference = fStore.collection("Utilisateurs").document(utilisateurId).collection("ToWatch");
        collectionReference
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                // Log.d(TAG, document.getId() + " => " + document.getData().containsKey("id_jw"));

                                String type_tmdb;
                                if (document.get("type_jw").equals("show")){
                                    type_tmdb="tv";
                                } else {
                                    type_tmdb="movie";
                                }
                                ConnexionAPI.getInstance()
                                        .getMovieApi()
                                        .getFilmWithID(type_tmdb, Integer.parseInt(String.valueOf(document.get("id_tmdb"))),TMDB_API_KEY,"fr-FR")
                                        .enqueue(new Callback<Film>() {
                                            @Override
                                            public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                                                final Film film = response.body();
                                                film.setType(String.valueOf(document.get("type_jw")));

                                                ConnexionAPI.getInstance()
                                                        .getJWApi()
                                                        .getDetails((String) document.get("type_jw"),Integer.parseInt(String.valueOf(document.get("id_jw"))))
                                                        .enqueue(new Callback<GroupeOffres>() {
                                                                     @Override
                                                                     public void onResponse(Call<GroupeOffres> call, Response<GroupeOffres> response) {

                                                                         GroupeOffres resultats = response.body();
                                                                         film.setListeOffres(resultats.getOffres());
                                                                         film.setAge(resultats.getAgeCertification());
                                                                         listeFilms.add(film);
                                                                         recyclerAdapter.notifyDataSetChanged();
                                                                     }

                                                                     @Override
                                                                     public void onFailure(Call<GroupeOffres> call, Throwable t) {

                                                                     }
                                                        });


                                            }

                                            @Override
                                            public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
                                                Log.e("Erreur","Oups, une erreur de requête");
                                                t.printStackTrace();
                                            }
                                        });
                            }
                        } else {
                            Log.d("Problème", "Error getting documents: ", task.getException());
                        }



                    }
                });


        recyclerAdapter = new ToWatchAdapter(getApplicationContext(), listeFilms);
        recyclerView.setAdapter(recyclerAdapter);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ToWatchAdapter.FilmViewHolder) {
            // get the removed item name to display it in snack bar
            final String name = listeFilms.get(viewHolder.getAdapterPosition()).getTitre();

            if(direction==ItemTouchHelper.LEFT) {
                // backup of removed item for undo purpose
                final Film deletedItem = listeFilms.get(viewHolder.getAdapterPosition());
                final int deletedIndex = viewHolder.getAdapterPosition();

                // remove the item from recycler view
                FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
                FirebaseFirestore fStore = FirebaseFirestore.getInstance();

                String utilisateurId = mFirebaseAuth.getCurrentUser().getUid();
                final CollectionReference collectionReference = fStore.collection("Utilisateurs").document(utilisateurId).collection("ToWatch");

                Query query = collectionReference.whereEqualTo("id_tmdb", deletedItem.getId());

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                collectionReference.document(document.getId()).delete();
                                Toast.makeText(WishlistActivity.this, name+" supprimé", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d("Probleme", "Error getting documents: ", task.getException());
                            Toast.makeText(WishlistActivity.this, "Echec de la suppression de "+name, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                recyclerAdapter.removeItem(viewHolder.getAdapterPosition());

            }else if(direction==ItemTouchHelper.RIGHT){

                recyclerAdapter.removeItem(viewHolder.getAdapterPosition());

                Log.e("MESSAGE",name+" a été vu !");

            }
        }
    }

}

package com.jennifertestu.whattowatch.communication;

import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.FilmsResultats;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("movie/{id}")
    public Call<Film> getFilmWithID(@Path("id") int id,@Query("api_key") String key,@Query("language") String langue);

    @GET("movie/{id}/similar")
    public Call<FilmsResultats> getFilmsSimilairesWithID(@Path("id") int id, @Query("api_key") String key, @Query("language") String langue);

    @GET("movie/{id}/recommendations")
    public Call<FilmsResultats> getFilmsRecommendationsWithID(@Path("id") int id, @Query("api_key") String key, @Query("language") String langue);

}

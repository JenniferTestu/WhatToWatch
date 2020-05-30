package com.jennifertestu.whattowatch.communication;

import com.jennifertestu.whattowatch.model.Credits;
import com.jennifertestu.whattowatch.model.Film;
import com.jennifertestu.whattowatch.model.FilmsResultats;
import com.jennifertestu.whattowatch.model.ReleaseResultats;
import com.jennifertestu.whattowatch.model.VideosResultats;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("{type}/{id}?append_to_response=videos%2Ccredits") // @GET("movie/{id}?append_to_response=credits")
    public Call<Film> getFilmWithID(@Path("type") String type, @Path("id") int id,@Query("api_key") String key,@Query("language") String langue);

    @GET("movie/{id}/similar")
    public Call<FilmsResultats> getFilmsSimilairesWithID(@Path("id") int id, @Query("api_key") String key, @Query("language") String langue);

    @GET("movie/{id}/recommendations")
    public Call<FilmsResultats> getFilmsRecommendationsWithID(@Path("id") int id, @Query("api_key") String key, @Query("language") String langue);

    @GET("{type}/{id}/credits")
    public Call<Credits> getCredits(@Path("type") String type, @Path("id") int id, @Query("api_key") String key);

    @GET("{type}/{id}/videos")
    public Call<VideosResultats> getVideosWithID(@Path("type") String type, @Path("id") int id, @Query("api_key") String key, @Query("language") String langue);

    @GET("movie/{id}/release_dates")
    public Call<ReleaseResultats> getReleaseDatesWithID(@Path("id") int id, @Query("api_key") String key);
}

package com.jennifertestu.whattowatch.communication;

import com.jennifertestu.whattowatch.model.OffresResultats;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JWApi {

    @GET("content/titles/fr_FR/popular")
    public Call<OffresResultats> getOffres(@Query("body") String key);
}

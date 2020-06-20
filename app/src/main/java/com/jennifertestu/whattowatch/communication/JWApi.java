package com.jennifertestu.whattowatch.communication;

import com.jennifertestu.whattowatch.model.GroupeOffres;
import com.jennifertestu.whattowatch.model.OffresResultats;
import com.jennifertestu.whattowatch.model.PersonnesResultats;
import com.jennifertestu.whattowatch.model.Recherche;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JWApi {

    @GET("content/titles/fr_FR/popular")
    public Call<OffresResultats> getOffres(@Query("body") String json);  // @Query("body") String key

    @GET("content/titles/{type}/{id}/locale/fr_FR?language=fr")
    public Call<GroupeOffres> getDetails(@Path("type") String type, @Path("id") int id);

    @GET("content/titles/fr_FR/popular")
    public Call<PersonnesResultats> getPersonnes(@Query("body") String json);

    @GET("content/titles/fr_FR/recommendations")
    public Call<OffresResultats> getRecommandations(@Query("jw_entity_ids") String jw_id, @Query("body") JSONObject json);
}

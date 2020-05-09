package com.jennifertestu.whattowatch.communication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnexionAPI {
    private static ConnexionAPI mInstance;
    private static final String TMDB_URL = "https://api.themoviedb.org/3/";
    private static final String JW_URL = "https://apis.justwatch.com/";
    private static final String BETA_URL = "https://api.betaseries.com/";

    private Retrofit mRetrofitTMDB;
    private Retrofit mRetrofitJW;

    private ConnexionAPI() {
        //Todo: A supprimer avant la release

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .readTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor);

        mRetrofitTMDB = new Retrofit.Builder()
                .baseUrl(TMDB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client((client.build()))
                .build();
        mRetrofitJW = new Retrofit.Builder()
                .baseUrl(JW_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client((client.build()))
                .build();
    }

    public static ConnexionAPI getInstance() {
        if (mInstance == null) {
            mInstance = new ConnexionAPI();
        }
        return mInstance;
    }

    public MovieApi getMovieApi() {
        return mRetrofitTMDB.create(MovieApi.class);
    }
    public JWApi getJWApi() {
        return mRetrofitJW.create(JWApi.class);
    }

}

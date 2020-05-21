package com.github.wassilkhetim.android;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickMortyApi {

    @GET("/api/character/")
    Call<RestRickmortyResponse> getRickmortyResponse(@Query("page") int valeur);

}

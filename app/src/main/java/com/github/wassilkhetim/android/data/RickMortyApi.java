package com.github.wassilkhetim.android.data;

import com.github.wassilkhetim.android.presentation.model.RestRickmortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RickMortyApi {

    @GET("/api/character/")
    Call<RestRickmortyResponse> getRickmortyResponse(@Query("page") int valeur);

}

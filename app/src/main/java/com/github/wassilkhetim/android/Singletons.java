package com.github.wassilkhetim.android;

import android.content.Context;
import android.content.SharedPreferences;

import com.github.wassilkhetim.android.data.RickMortyApi;
import com.github.wassilkhetim.android.presentation.model.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Singletons {

    private static Gson gsonInstance;
    private static RickMortyApi rickMortyApiInstance;
    private static SharedPreferences sharedPreferences;

    public static Gson getGson(){
        if(gsonInstance == null){
            gsonInstance = new GsonBuilder().setLenient().create();
        }
        return gsonInstance;
    }

    public static RickMortyApi getRickMortyApi(){
        if(rickMortyApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            rickMortyApiInstance = retrofit.create(RickMortyApi.class);
        }

        return rickMortyApiInstance;
    }

    public static SharedPreferences getGetSharedPreferences(Context context){
        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("application_rickmorty_esiea", Context.MODE_PRIVATE);
        }
        return sharedPreferences;
    }

}

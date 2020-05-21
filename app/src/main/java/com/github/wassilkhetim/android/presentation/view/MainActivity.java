package com.github.wassilkhetim.android.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.wassilkhetim.android.R;
import com.github.wassilkhetim.android.data.RickMortyApi;
import com.github.wassilkhetim.android.presentation.model.PersonnageInfo;
import com.github.wassilkhetim.android.presentation.model.RestRickmortyResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String BASE_URL = "https://rickandmortyapi.com";
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<PersonnageInfo> listPersonnage;
    private SharedPreferences sharedPreferences;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_rickmorty_esiea", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        this.listPersonnage = getDataFromCache();
        if(this.listPersonnage != null){
            startRecyclerView();
        }else{
            DownloadData();
        }
        
        Log.d("WASSA", "startApiCall Worked");


    }

    private void DownloadData(){
        this.listPersonnage = new ArrayList<PersonnageInfo>();
        Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG).show();
        startApiCall(1);
    }

    private List<PersonnageInfo> getDataFromCache() {
        String jsonPersonnageInfoList = sharedPreferences.getString("jsonPersonnageInfoList", null);

        if(jsonPersonnageInfoList == null){
            return null;
        }else{
            Type listType = new TypeToken<List<PersonnageInfo>>(){}.getType();
            return gson.fromJson(jsonPersonnageInfoList, listType);
        }
    }

    private void startRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(this.listPersonnage);
        recyclerView.setAdapter(mAdapter);
    }

    private void startApiCall(final int page) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RickMortyApi rickMortyApi = retrofit.create(RickMortyApi.class);

        Call<RestRickmortyResponse> call = rickMortyApi.getRickmortyResponse(page);
        call.enqueue(new Callback<RestRickmortyResponse>() {
            @Override
            public void onResponse(Call<RestRickmortyResponse> call, Response<RestRickmortyResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<PersonnageInfo> personnageInfoList = response.body().getResults();

                    addElementToList(personnageInfoList);
                    if(response.body().getInfo().getNext() != null && !response.body().getInfo().getNext().equals("")){
                        startApiCall(page+1);
                    }else{
                        saveList();
                        startRecyclerView();
                        Toast.makeText(getApplicationContext(), "Loading Success", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("WASSA", "startRecyclerView Worked");
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestRickmortyResponse> call, Throwable t) {
                Log.d("WASSA", t.getMessage());
                showError();
            }
        });
    }

    private void saveList() {
        String jsonString = gson.toJson(this.listPersonnage);
        sharedPreferences
                .edit()
                .putString("jsonPersonnageInfoList", jsonString)
                .apply();
        Toast.makeText(this, "Liste sauvegardée", Toast.LENGTH_SHORT).show();
    }

    private void addElementToList(List<PersonnageInfo> newList){
        this.listPersonnage.addAll(newList);
    }

    private void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_LONG).show();
    }

}

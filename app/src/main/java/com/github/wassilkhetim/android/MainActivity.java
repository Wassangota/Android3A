package com.github.wassilkhetim.android;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.listPersonnage = new ArrayList<PersonnageInfo>();
        Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_LONG).show();
        startApiCall(1);
        Log.d("WASSA", "startApiCall Worked");


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
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

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

                    //Log.d("WASSA", personnageInfoList.toString());
                    //startRecyclerView(personnageInfoList);
                    addElementToList(personnageInfoList);
                    if(response.body().getInfo().getNext() != null && !response.body().getInfo().getNext().equals("")){
                        startApiCall(page+1);
                    }else{
                        startRecyclerView();
                        Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
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

    private void addElementToList(List<PersonnageInfo> newList){
        this.listPersonnage.addAll(newList);
    }

    private void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_LONG).show();
    }

}

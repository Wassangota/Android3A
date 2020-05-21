package com.github.wassilkhetim.android.presentation.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.wassilkhetim.android.R;
import com.github.wassilkhetim.android.Singletons;
import com.github.wassilkhetim.android.data.RickMortyApi;
import com.github.wassilkhetim.android.presentation.controller.MainController;
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

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SharedPreferences sharedPreferences;
    Gson gson;

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(this,
                Singletons.getGson(),
                Singletons.getGetSharedPreferences(getApplicationContext()));
        controller.onStart();
    }

    public void startRecyclerView(List<PersonnageInfo> listPersonnage) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new ListAdapter(listPersonnage);
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(this, "API Error", Toast.LENGTH_LONG).show();
    }

}

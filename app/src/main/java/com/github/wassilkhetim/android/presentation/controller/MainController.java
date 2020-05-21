package com.github.wassilkhetim.android.presentation.controller;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.github.wassilkhetim.android.Singletons;
import com.github.wassilkhetim.android.presentation.model.PersonnageInfo;
import com.github.wassilkhetim.android.presentation.model.RestRickmortyResponse;
import com.github.wassilkhetim.android.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;
    private List<PersonnageInfo> listPersonnage;

    public MainController(MainActivity v,Gson gson, SharedPreferences sharedPreferences){
        this.view = v;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){
        this.listPersonnage = getDataFromCache();
        if(listPersonnage != null){
            view.startRecyclerView(listPersonnage);
        }else{
            DownloadData();
        }

        Log.d("WASSA", "startApiCall Worked");
    }

    public void onClose(){

    }

    private void startApiCall(final int page) {
        Call<RestRickmortyResponse> call = Singletons.getRickMortyApi().getRickmortyResponse(page);
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
                        view.startRecyclerView(getListPersonnage());
                        Toast.makeText(view, "Loading Success", Toast.LENGTH_SHORT).show();
                    }
                    Log.d("WASSA", "startRecyclerView Worked");
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestRickmortyResponse> call, Throwable t) {
                Log.d("WASSA", t.getMessage());
                view.showError();
            }
        });
    }

    private void saveList() {
        String jsonString = gson.toJson(getListPersonnage());
        sharedPreferences
                .edit()
                .putString("jsonPersonnageInfoList", jsonString)
                .apply();
        Toast.makeText(view, "Liste sauvegardée", Toast.LENGTH_SHORT).show();
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

    private void DownloadData(){
        initListPersonnage();
        Toast.makeText(view.getApplicationContext(), "Loading", Toast.LENGTH_LONG).show();
        startApiCall(1);
    }

    public void onItemClick(PersonnageInfo personnageInfo){

    }

    public List<PersonnageInfo> getListPersonnage(){
        return listPersonnage;
    }

    public void initListPersonnage(){
        this.listPersonnage = new ArrayList<PersonnageInfo>();
    }

    public void addElementToList(List<PersonnageInfo> newList){
        this.listPersonnage.addAll(newList);
    }

}

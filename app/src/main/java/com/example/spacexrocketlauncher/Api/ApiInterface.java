package com.example.spacexrocketlauncher.Api;

import com.example.spacexrocketlauncher.Models.Launch;
import com.example.spacexrocketlauncher.Models.Rocket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("rockets")
    Call<List<Rocket>> getRockets();

    @GET("launches")
    Call<List<Launch>> getLaunches(@Query("rocket_name") String rocket_name);
}

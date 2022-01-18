package com.shashank.mvvm.Network;

import com.shashank.mvvm.Modals.actors;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("photos?albumId=1")
    Call<List<actors>> getAllActors();
}

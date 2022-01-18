package com.shashank.mvvm;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.mvvm.Adapter.ActorAdapter;
import com.shashank.mvvm.Modals.actors;
import com.shashank.mvvm.Network.Api;
import com.shashank.mvvm.Repository.ActorsRepository;
import com.shashank.mvvm.ViewModel.ActorsViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView data;
    private ActorAdapter actorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = findViewById(R.id.rv);
        data.setHasFixedSize(true);
        data.setLayoutManager(new LinearLayoutManager(this));
        data.setItemAnimator(new DefaultItemAnimator());
        //
        List<com.shashank.mvvm.Modals.actors> actors = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actors);

        //
        ActorsRepository actorsRepository = new ActorsRepository(getApplication());

        ActorsViewModel actorsViewModel = new ViewModelProvider(this).get(ActorsViewModel.class);
        actorsViewModel.getAllActors()
                .observe(this, actorsList -> {
                    actorAdapter.getAllActors(actorsList);
                    data.setAdapter(actorAdapter);
                });
    }
}
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

    private ActorsViewModel actorsViewModel;
    private RecyclerView data;
    private ActorAdapter actorAdapter;
    private ActorsRepository actorsRepository;
    private static String URL = "https://jsonplaceholder.typicode.com/";
    private List<actors> actors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = findViewById(R.id.rv);
        data.setHasFixedSize(true);
        data.setLayoutManager(new LinearLayoutManager(this));
        data.setItemAnimator(new DefaultItemAnimator());
        //
        actors = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actors);

        //
        actorsRepository = new ActorsRepository(getApplication());

        actorsViewModel = new ViewModelProvider(this).get(ActorsViewModel.class);
        actorsViewModel.getAllActors()
                .observe(this, actorsList -> {
                    data.setAdapter(actorAdapter);
                    actorAdapter.getAllActors(actorsList);
//                    Log.i("yo", "onCreate: "+actorsList);
                });
        networkRequest();
    }

    private void networkRequest() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<actors>> call = api.getAllActors();
        call.enqueue(new Callback<List<actors>>() {
            @Override
            public void onResponse(Call<List<actors>> call, Response<List<actors>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"working",Toast.LENGTH_SHORT).show();
                    actorsRepository.insert(response.body());
//                    Log.d("main", "onResponse: "+ response.body());
                } else {
                    Toast.makeText(MainActivity.this,"notworking",Toast.LENGTH_SHORT).show();
//                    Log.d("main", "onResponse: "+ response.body());
                }
            }

            @Override
            public void onFailure(Call<List<actors>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
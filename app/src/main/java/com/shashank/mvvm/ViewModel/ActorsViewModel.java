package com.shashank.mvvm.ViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.shashank.mvvm.MainActivity;
import com.shashank.mvvm.Modals.actors;
import com.shashank.mvvm.Network.Api;
import com.shashank.mvvm.Repository.ActorsRepository;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActorsViewModel extends AndroidViewModel {

    private ActorsRepository actorsRepository;
    private LiveData<List<actors>> getAllActors;
    private Application application;

    public ActorsViewModel(@NonNull Application application) {
        super(application);

        actorsRepository=new ActorsRepository(application);
        getAllActors=actorsRepository.getAllActors();
    }

    public void insert(List<actors> list)
    {
        actorsRepository.insert(list);
    }

    public LiveData<List<actors>> getAllActors()
    {
        return getAllActors;
    }

}


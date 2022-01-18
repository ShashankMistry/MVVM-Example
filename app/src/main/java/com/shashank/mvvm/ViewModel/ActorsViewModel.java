package com.shashank.mvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.shashank.mvvm.Modals.actors;
import com.shashank.mvvm.Repository.ActorsRepository;


import java.util.List;

public class ActorsViewModel extends AndroidViewModel {

    private ActorsRepository actorsRepository;
    private LiveData<List<actors>> getAllActors;

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


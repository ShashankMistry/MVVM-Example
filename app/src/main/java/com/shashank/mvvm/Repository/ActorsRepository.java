package com.shashank.mvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.shashank.mvvm.Dao.ActorsDao;
import com.shashank.mvvm.Database.ActorsDatabase;
import com.shashank.mvvm.Modals.actors;
import com.shashank.mvvm.Network.Api;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActorsRepository {
    private ActorsDatabase database;
    private LiveData<List<actors>> getAllActors;
    private Application application;
    private static String URL = "https://jsonplaceholder.typicode.com/";


    public ActorsRepository(Application application)
    {
        this.application = application;
        database=ActorsDatabase.getInstance(application);
        getAllActors=database.actorsDao().getAllActors();
    }

    public void insert(List<actors> actorList){
        new InsertAsyncTask(database).execute(actorList);
    }

    public LiveData<List<actors>> getAllActors()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<List<actors>> call = api.getAllActors();
        final MutableLiveData<List<actors>> mutableLiveData= new MutableLiveData<>();
        call.enqueue(new Callback<List<actors>>() {
            @Override
            public void onResponse(Call<List<actors>> call, Response<List<actors>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(application,"working",Toast.LENGTH_SHORT).show();
                    mutableLiveData.setValue(response.body());
                    insert(response.body());
                } else {
                    Toast.makeText(application,"notworking",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<actors>> call, Throwable t) {
                Toast.makeText(application, "Something went wrong!!", Toast.LENGTH_SHORT).show();
            }
        });
        if (!isInternetAvailable()){
//            Toast.makeText(application, "Internet is not available!!", Toast.LENGTH_SHORT).show();
            return getAllActors;
        } else {
            return mutableLiveData;
        }
    }

    static class InsertAsyncTask extends AsyncTask<List<actors>,Void,Void>{
        private ActorsDao actorDao;
        InsertAsyncTask(ActorsDatabase actorDatabase)
        {
            actorDao=actorDatabase.actorsDao();
        }
        @Override
        protected Void doInBackground(List<actors>... lists) {
            actorDao.deleteAll();
            actorDao.insert(lists[0]);
            return null;
        }
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }
}

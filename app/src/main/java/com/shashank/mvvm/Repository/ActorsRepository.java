package com.shashank.mvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.shashank.mvvm.Dao.ActorsDao;
import com.shashank.mvvm.Database.ActorsDatabase;
import com.shashank.mvvm.Modals.actors;

import java.util.List;

public class ActorsRepository {
    private ActorsDatabase database;
    private LiveData<List<actors>> getAllActors;

    public ActorsRepository(Application application)
    {
        database=ActorsDatabase.getInstance(application);
        getAllActors=database.actorsDao().getAllActors();
    }

    public void insert(List<actors> actorList){
        new InsertAsyncTask(database).execute(actorList);
    }

    public LiveData<List<actors>> getAllActors()
    {
        return getAllActors;
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
}

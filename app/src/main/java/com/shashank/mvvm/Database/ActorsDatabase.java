package com.shashank.mvvm.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.shashank.mvvm.Dao.ActorsDao;
import com.shashank.mvvm.MainActivity;
import com.shashank.mvvm.Modals.actors;

@Database(entities = {actors.class}, version = 3, exportSchema = false)
public abstract class ActorsDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "actors";
    public abstract ActorsDao actorsDao();
    private static volatile ActorsDatabase INSTANCE;


    public static ActorsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ActorsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE=Room.databaseBuilder(context, ActorsDatabase.class, DATABASE_NAME).addCallback(callback).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(INSTANCE);
        }
    };

    static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private final ActorsDao actorDao;

        PopulateAsyncTask(ActorsDatabase actorsDatabase) {
            actorDao = actorsDatabase.actorsDao();
            Log.i("Test", "PopulateAsyncTask: test");

        }

        @Override
        protected Void doInBackground(Void... voids) {
            actorDao.deleteAll();
            Log.w("main123", "doInBackground: bg working");
            return null;
        }
    }
}

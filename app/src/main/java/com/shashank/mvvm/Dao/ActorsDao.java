package com.shashank.mvvm.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.shashank.mvvm.Modals.actors;

import java.util.List;

@Dao
public interface ActorsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<actors> actorList);

    @Query("SELECT * FROM actors")
    LiveData<List<actors>> getAllActors();

    @Query("DELETE FROM actors")
    void deleteAll();
}

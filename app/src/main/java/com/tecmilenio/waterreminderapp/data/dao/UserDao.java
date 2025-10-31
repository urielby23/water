package com.tecmilenio.waterreminderapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tecmilenio.waterreminderapp.data.entities.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM usuario LIMIT 1")
    LiveData<User> getUser();

    @Query("DELETE FROM usuario")
    void deleteAll();
}

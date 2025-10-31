package com.tecmilenio.waterreminderapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.tecmilenio.waterreminderapp.data.WaterDatabase;
import com.tecmilenio.waterreminderapp.data.dao.UserDao;
import com.tecmilenio.waterreminderapp.data.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserViewModel extends AndroidViewModel {

    private final UserDao userDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public UserViewModel(@NonNull Application application) {
        super(application);
        userDao = WaterDatabase.getDatabase(application).userDao();
    }

    public LiveData<User> getUser() {
        return userDao.getUser();
    }

    public void insertUser(User user) {
        executor.execute(() -> userDao.insert(user));
    }

    public void updateUser(User user) {
        executor.execute(() -> userDao.update(user));
    }

    public void deleteUser() {
        executor.execute(userDao::deleteAll);
    }
}

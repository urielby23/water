package com.tecmilenio.waterreminderapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tecmilenio.waterreminderapp.data.dao.UserDao;
import com.tecmilenio.waterreminderapp.data.dao.WaterIntakeDao;
import com.tecmilenio.waterreminderapp.data.entities.User;
import com.tecmilenio.waterreminderapp.data.entities.WaterIntake;

@Database(entities = {WaterIntake.class, User.class}, version = 2)
public abstract class WaterDatabase extends RoomDatabase {

    private static volatile WaterDatabase INSTANCE;

    public abstract WaterIntakeDao waterIntakeDao();
    public abstract UserDao userDao();

    public static WaterDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WaterDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    WaterDatabase.class,
                                    "water_reminder_db"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

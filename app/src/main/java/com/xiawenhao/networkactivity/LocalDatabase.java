package com.xiawenhao.networkactivity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Person.class}, version = 1,exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}

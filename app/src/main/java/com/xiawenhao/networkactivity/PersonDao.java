package com.xiawenhao.networkactivity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Person... person);

    @Delete
    void delete(Person person);
}

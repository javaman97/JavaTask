package com.aman.javatask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert
    void insert(Task task);

    @Delete
    void deleteTask(Task task);


    @Update
    void updateTask(Task task);

    @Query("SELECT * from task_table")
    LiveData<List<Task>> getAllTasks();


}

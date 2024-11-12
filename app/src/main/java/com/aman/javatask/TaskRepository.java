package com.aman.javatask;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Application application) {
      TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
      taskDAO = taskDatabase.taskDAO();
      allTasks = taskDAO.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks(){
        return  allTasks;
    }


    public void insert(Task task){
        new Thread(() -> taskDAO.insert(task)).start();
    }

    public void delete(Task task){
        new Thread(() -> taskDAO.deleteTask(task)).start();
    }

}

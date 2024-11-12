package com.aman.javatask;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<List<Task>> alltasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        alltasks = taskRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAlltasks(){
        return alltasks;
    }

    public void addTask(String title, String desc){
        Task task = new Task(title,desc);
        taskRepository.insert(task);
    }
    public void deleteTask(String title, String desc){
        Task task = new Task(title,desc);
        taskRepository.delete(task);
    }
}

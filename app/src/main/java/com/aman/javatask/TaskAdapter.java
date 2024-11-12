package com.aman.javatask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public  class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{

   private List<Task> taskList;
    private TaskDAO taskDAO;
    private TaskViewModel taskViewModel;
    public TaskAdapter(List<Task> taskList, TaskDAO taskDAO, TaskViewModel taskViewModel) {
        this.taskList = taskList;
        this.taskDAO = taskDAO;
        this.taskViewModel = taskViewModel;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_task, parent,false);

        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.title.setText(task.getTitle());
        holder.desc.setText(task.getDesc());

        holder.deleteBtn.setOnClickListener(
                v->{
                    taskViewModel.deleteTask(task.getTitle(), task.getDesc());
                  taskList.remove(position);
                  notifyItemRemoved(position);
                  taskDAO.deleteTask(task);
                }
        );

    }

    public  void updateData(List<Task> newList){
        this.taskList = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
       public TextView title, desc;
        public ImageButton editBtn, deleteBtn;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txtTitle);
            desc = itemView.findViewById(R.id.txtDes);
            deleteBtn = itemView.findViewById(R.id.delete);
            editBtn = itemView.findViewById(R.id.edit);
        }
    }
}

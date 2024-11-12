package com.aman.javatask;

import android.app.Application;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
 private TaskViewModel taskViewModel;
 private TaskDAO taskDAO;
 private TaskAdapter taskAdapter;
 private RecyclerView recyclerView;
 private EditText etxTitle,etxtDesc;
 private ImageButton deleteBtn, editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etxTitle = findViewById(R.id.etxtTitle);
        etxtDesc = findViewById(R.id.etxtDesc);
        recyclerView = findViewById(R.id.rcVTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        TaskDatabase db = TaskDatabase.getDatabase(this);
        taskDAO = db.taskDAO();

        // Observe LiveData
        taskViewModel.getAlltasks().observe(this, tasks -> {
            if (taskAdapter == null) {
                taskAdapter = new TaskAdapter(tasks,taskDAO, taskViewModel);
                recyclerView.setAdapter(taskAdapter);
            } else {
                taskAdapter.updateData(tasks);
            }
        });
        Button addButton = findViewById(R.id.button);

        addButton.setOnClickListener(v->{
            String title = etxTitle.getText().toString();
            String desc = etxtDesc.getText().toString();

            // Add the task via ViewModel
            if (!title.isEmpty() && !desc.isEmpty()) {
                taskViewModel.addTask(title, desc);
                etxTitle.setText("");  // Clear the EditText fields after adding
                etxtDesc.setText("");
            } else {
                Toast.makeText(getApplicationContext(),"Please Enter Title & Desc", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
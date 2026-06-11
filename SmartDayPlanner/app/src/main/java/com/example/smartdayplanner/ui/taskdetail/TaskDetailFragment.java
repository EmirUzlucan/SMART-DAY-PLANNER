package com.example.smartdayplanner.ui.taskdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.smartdayplanner.R;
import com.example.smartdayplanner.databinding.FragmentTaskDetailBinding;
import com.example.smartdayplanner.data.TaskPreferences;
import com.example.smartdayplanner.model.Task;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TaskDetailFragment extends Fragment {

    private FragmentTaskDetailBinding binding;
    private TaskPreferences taskPreferences;
    private Task currentTask;
    private Calendar taskDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTaskDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        taskPreferences = new TaskPreferences(requireContext());
        taskDate = Calendar.getInstance();
        
        String taskName = getArguments() != null ? getArguments().getString("taskName") : null;
        if (taskName != null) {
            loadTaskData(taskName);
        }

        binding.btnDatePicker.setOnClickListener(v -> showDatePicker());

        binding.btnUpdate.setOnClickListener(v -> {
            if (currentTask != null) {
                String name = binding.editTaskName.getText() != null ? binding.editTaskName.getText().toString() : "";
                String durationStr = binding.editDuration.getText() != null ? binding.editDuration.getText().toString() : "";
                int duration = durationStr.isEmpty() ? 0 : Integer.parseInt(durationStr);
                String period = binding.spinnerPeriod.getSelectedItem().toString();
                String priority = binding.spinnerPriority.getSelectedItem().toString();
                String date = dateFormat.format(taskDate.getTime());
                
                Task newTask = new Task(name, duration, priority, date, period, currentTask.isCompleted());
                taskPreferences.updateTask(currentTask, newTask);
                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.btnDelete.setOnClickListener(v -> {
            if (currentTask != null) {
                taskPreferences.deleteTask(currentTask);
                Navigation.findNavController(v).popBackStack();
            }
        });

        return root;
    }

    private void updateDateButtonText() {
        binding.btnDatePicker.setText(dateFormat.format(taskDate.getTime()));
    }

    private void showDatePicker() {
        new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
            taskDate.set(Calendar.YEAR, year);
            taskDate.set(Calendar.MONTH, month);
            taskDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateButtonText();
        }, taskDate.get(Calendar.YEAR), taskDate.get(Calendar.MONTH), 
           taskDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void loadTaskData(String taskName) {
        List<Task> tasks = taskPreferences.getTasks();
        for (Task task : tasks) {
            if (task.getName().equals(taskName)) {
                currentTask = task;
                binding.editTaskName.setText(task.getName());
                binding.editDuration.setText(String.valueOf(task.getDurationMinutes()));
                
                try {
                    taskDate.setTime(dateFormat.parse(task.getDate()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updateDateButtonText();
                
                // Spinner setSelection logic
                String[] periods = getResources().getStringArray(R.array.task_periods);
                for (int i = 0; i < periods.length; i++) {
                    if (periods[i].equals(task.getPeriod())) {
                        binding.spinnerPeriod.setSelection(i);
                        break;
                    }
                }

                String[] priorities = getResources().getStringArray(R.array.priority_levels);
                for (int i = 0; i < priorities.length; i++) {
                    if (priorities[i].equals(task.getPriority())) {
                        binding.spinnerPriority.setSelection(i);
                        break;
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
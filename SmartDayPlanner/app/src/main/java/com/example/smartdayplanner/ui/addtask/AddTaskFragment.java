package com.example.smartdayplanner.ui.addtask;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.smartdayplanner.databinding.FragmentAddTaskBinding;
import com.example.smartdayplanner.data.TaskPreferences;
import com.example.smartdayplanner.model.Task;
import android.app.DatePickerDialog;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTaskFragment extends Fragment {

    private FragmentAddTaskBinding binding;
    private TaskPreferences taskPreferences;
    private Calendar taskDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        taskPreferences = new TaskPreferences(requireContext());
        taskDate = Calendar.getInstance();
        
        updateDateButtonText();

        binding.btnDatePicker.setOnClickListener(v -> showDatePicker());

        binding.btnSave.setOnClickListener(v -> {
            String name = binding.editTaskName.getText() != null ? binding.editTaskName.getText().toString() : "";
            String durationStr = binding.editDuration.getText() != null ? binding.editDuration.getText().toString() : "";
            int duration = durationStr.isEmpty() ? 0 : Integer.parseInt(durationStr);
            String period = binding.spinnerPeriod.getSelectedItem().toString();
            String priority = binding.spinnerPriority.getSelectedItem().toString();
            String date = dateFormat.format(taskDate.getTime());

            if (!name.isEmpty()) {
                Task newTask = new Task(name, duration, priority, date, period, false);
                taskPreferences.addTask(newTask);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
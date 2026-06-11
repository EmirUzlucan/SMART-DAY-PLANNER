package com.example.smartdayplanner.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.smartdayplanner.R;
import com.example.smartdayplanner.databinding.FragmentHomeBinding;
import com.example.smartdayplanner.model.Task;
import com.example.smartdayplanner.ui.adapter.TaskAdapter;
import com.example.smartdayplanner.data.TaskPreferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import android.app.DatePickerDialog;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TaskPreferences taskPreferences;
    private Calendar selectedDate;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private final SimpleDateFormat displayFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("tr"));
    private final List<TaskAdapter> adapters = new ArrayList<>();
    private boolean isGlobalSelectionMode = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        taskPreferences = new TaskPreferences(requireContext());
        selectedDate = Calendar.getInstance();
        
        updateDateDisplay();

        binding.btnPickDate.setOnClickListener(v -> showDatePicker());

        binding.fabAddTask.setOnClickListener(v -> 
            Navigation.findNavController(v).navigate(R.id.action_home_to_addTask)
        );

        binding.btnDeleteSelected.setOnClickListener(v -> deleteSelectedTasks());

        binding.btnSelectAll.setOnClickListener(v -> selectAllTasks());

        binding.btnCloseSelection.setOnClickListener(v -> setAllSelectionMode(false));

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (isGlobalSelectionMode) {
                    setAllSelectionMode(false);
                } else {
                    setEnabled(false);
                    requireActivity().getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });

        return root;
    }

    private void deleteSelectedTasks() {
        Set<Task> tasksToDelete = new HashSet<>();
        for (TaskAdapter adapter : adapters) {
            tasksToDelete.addAll(adapter.getSelectedTasks());
        }
        
        if (!tasksToDelete.isEmpty()) {
            for (Task task : tasksToDelete) {
                taskPreferences.deleteTask(task);
            }
            setAllSelectionMode(false);
            loadTasks();
        }
    }

    private void selectAllTasks() {
        for (TaskAdapter adapter : adapters) {
            adapter.selectAll();
        }
        // Selection changed listener in adapter will trigger updateSelectionUI via totalSelected
    }

    private void setAllSelectionMode(boolean enabled) {
        isGlobalSelectionMode = enabled;
        for (TaskAdapter adapter : adapters) {
            adapter.setSelectionMode(enabled);
        }
        updateSelectionUI(0);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadTasks();
    }

    private void updateDateDisplay() {
        binding.textDate.setText(displayFormat.format(selectedDate.getTime()));
    }

    private void showDatePicker() {
        new DatePickerDialog(requireContext(), (view, year, month, dayOfMonth) -> {
            selectedDate.set(Calendar.YEAR, year);
            selectedDate.set(Calendar.MONTH, month);
            selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateDisplay();
            loadTasks();
        }, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), 
           selectedDate.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void loadTasks() {
        String dateStr = dateFormat.format(selectedDate.getTime());
        List<Task> allTasks = taskPreferences.getTasks();
        
        List<Task> morningTasks = new ArrayList<>();
        List<Task> afternoonTasks = new ArrayList<>();
        List<Task> eveningTasks = new ArrayList<>();
        
        for (Task task : allTasks) {
            if (task.getDate().equals(dateStr)) {
                String period = task.getPeriod() != null ? task.getPeriod() : "Sabah";
                switch (period) {
                    case "Sabah":
                        morningTasks.add(task);
                        break;
                    case "Öğle":
                        afternoonTasks.add(task);
                        break;
                    case "Akşam":
                        eveningTasks.add(task);
                        break;
                    default:
                        morningTasks.add(task);
                        break;
                }
            }
        }

        adapters.clear();
        setupRecyclerView(binding.recyclerMorning, morningTasks);
        setupRecyclerView(binding.recyclerAfternoon, afternoonTasks);
        setupRecyclerView(binding.recyclerEvening, eveningTasks);
        
        // Restore selection mode if it was active
        if (isGlobalSelectionMode) {
            for (TaskAdapter adapter : adapters) {
                adapter.setSelectionMode(true);
            }
        }
    }

    private void setupRecyclerView(androidx.recyclerview.widget.RecyclerView recyclerView, List<Task> tasks) {
        TaskAdapter adapter = new TaskAdapter(tasks, new TaskAdapter.OnTaskActionListener() {
            @Override
            public void onTaskClick(Task task) {
                Bundle bundle = new Bundle();
                bundle.putString("taskName", task.getName());
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_home_to_taskDetail, bundle);
            }

            @Override
            public void onSelectionChanged(int count) {
                int totalSelected = 0;
                for (TaskAdapter a : adapters) {
                    totalSelected += a.getSelectedTasks().size();
                }
                
                if (totalSelected == 0) {
                    setAllSelectionMode(false);
                } else {
                    updateSelectionUI(totalSelected);
                }
            }

            @Override
            public void onLongClick(Task task) {
                setAllSelectionMode(true);
                // Only toggle selection for the adapter that actually contains the task
                for (TaskAdapter a : adapters) {
                    a.toggleSelection(task); 
                }
            }
        });
        adapters.add(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void updateSelectionUI(int totalSelected) {
        if (totalSelected > 0) {
            binding.textDate.setText(totalSelected + " Görev Seçildi");
            binding.btnDeleteSelected.setVisibility(View.VISIBLE);
            binding.btnSelectAll.setVisibility(View.VISIBLE);
            binding.btnCloseSelection.setVisibility(View.VISIBLE);
            binding.btnPickDate.setVisibility(View.GONE);
        } else {
            updateDateDisplay();
            binding.btnDeleteSelected.setVisibility(View.GONE);
            binding.btnSelectAll.setVisibility(View.GONE);
            binding.btnCloseSelection.setVisibility(View.GONE);
            binding.btnPickDate.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
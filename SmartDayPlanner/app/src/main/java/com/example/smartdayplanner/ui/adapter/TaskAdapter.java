package com.example.smartdayplanner.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartdayplanner.databinding.ItemTaskBinding;
import com.example.smartdayplanner.model.Task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final List<Task> tasks;
    private final OnTaskActionListener listener;
    private final Set<Task> selectedTasks = new HashSet<>();
    private boolean isSelectionMode = false;

    public interface OnTaskActionListener {
        void onTaskClick(Task task);
        void onSelectionChanged(int count);
        void onLongClick(Task task);
    }

    public TaskAdapter(List<Task> tasks, OnTaskActionListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTaskBinding binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task, selectedTasks.contains(task), listener);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    /**
     * Seçim modunu açar veya kapatır. Kapatıldığında seçili öğeleri temizler.
     */
    public void setSelectionMode(boolean selectionMode) {
        this.isSelectionMode = selectionMode;
        if (!selectionMode) {
            selectedTasks.clear();
        }
        notifyDataSetChanged(); // Mod değişince tüm listeyi görsel olarak tazeliyoruz
    }

    public Set<Task> getSelectedTasks() {
        return selectedTasks;
    }

    /**
     * Listedeki tüm görevleri seçili hale getirir.
     */
    public void selectAll() {
        selectedTasks.clear();
        selectedTasks.addAll(tasks);
        notifyDataSetChanged();
        listener.onSelectionChanged(selectedTasks.size());
    }

    /**
     * Tek bir görevin seçim durumunu değiştirir (seç/seçimi kaldır).
     */
    public void toggleSelection(Task task) {
        int index = tasks.indexOf(task);
        if (index != -1) {
            if (selectedTasks.contains(task)) {
                selectedTasks.remove(task);
            } else {
                selectedTasks.add(task);
            }
            notifyItemChanged(index);
            listener.onSelectionChanged(selectedTasks.size());
        }
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final ItemTaskBinding binding;

        public TaskViewHolder(ItemTaskBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Task task, boolean isSelected, OnTaskActionListener listener) {
            binding.textTaskName.setText(task.getName());
            binding.textDuration.setText("Süre: " + task.getDurationMinutes() + " dk");
            binding.textPriority.setText("Öncelik: " + task.getPriority());
            binding.checkboxCompleted.setChecked(task.isCompleted());
            
            itemView.setBackgroundColor(isSelected ? Color.LTGRAY : Color.TRANSPARENT);

            // Seçim modu aktifken CheckBox tıklandığında durumu değiştirmek yerine görevi seç/kaldır yapıyoruz
            binding.checkboxCompleted.setOnClickListener(v -> {
                if (isSelectionMode) {
                    binding.checkboxCompleted.setChecked(!binding.checkboxCompleted.isChecked()); 
                    toggleSelection(task);
                } else {
                    task.setCompleted(binding.checkboxCompleted.isChecked());
                }
            });

            itemView.setOnClickListener(v -> {
                if (isSelectionMode) {
                    toggleSelection(task);
                } else {
                    listener.onTaskClick(task);
                }
            });

            itemView.setOnLongClickListener(v -> {
                if (!isSelectionMode) {
                    listener.onLongClick(task);
                    return true;
                }
                return false;
            });
        }
    }
}
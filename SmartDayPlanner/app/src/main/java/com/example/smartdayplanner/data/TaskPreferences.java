package com.example.smartdayplanner.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartdayplanner.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskPreferences {
    private static final String PREF_NAME = "SmartDayPlannerPrefs";
    private static final String KEY_TASKS = "tasks";
    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public TaskPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    public void saveTasks(List<Task> tasks) {
        String json = gson.toJson(tasks);
        sharedPreferences.edit().putString(KEY_TASKS, json).apply();
    }

    public List<Task> getTasks() {
        String json = sharedPreferences.getString(KEY_TASKS, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<List<Task>>() {}.getType();
        return gson.fromJson(json, type);
    }

    public void addTask(Task task) {
        List<Task> tasks = getTasks();
        tasks.add(task);
        saveTasks(tasks);
    }

    public void addTasks(List<Task> newTasks) {
        List<Task> tasks = getTasks();
        tasks.addAll(newTasks);
        saveTasks(tasks);
    }

    public void replaceTasksForDates(Map<String, List<Task>> tasksByDate) {
        List<Task> allTasks = getTasks();
        // Belirtilen tarihlerdeki eski görevleri sil
        allTasks.removeIf(task -> tasksByDate.containsKey(task.getDate()));
        // Yeni listeleri ekle
        for (List<Task> dayTasks : tasksByDate.values()) {
            allTasks.addAll(dayTasks);
        }
        saveTasks(allTasks);
    }

    public void deleteTask(Task task) {
        List<Task> tasks = getTasks();
        tasks.removeIf(t -> t.getName().equals(task.getName()) && t.getDate().equals(task.getDate()));
        saveTasks(tasks);
    }

    public void updateTask(Task oldTask, Task newTask) {
        List<Task> tasks = getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getName().equals(oldTask.getName()) && t.getDate().equals(oldTask.getDate())) {
                tasks.set(i, newTask);
                break;
            }
        }
        saveTasks(tasks);
    }
}
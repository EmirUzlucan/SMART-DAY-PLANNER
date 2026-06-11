package com.example.smartdayplanner.model;

public class Task {
    private String name;
    private int durationMinutes;
    private String priority;
    private String date;
    private String period; // Sabah, Öğle, Akşam
    private boolean isCompleted;

    public Task(String name, int durationMinutes, String priority, String date, String period, boolean isCompleted) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.priority = priority;
        this.date = date;
        this.period = period;
        this.isCompleted = isCompleted;
    }

    public String getName() { return name; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getPriority() { return priority; }
    public String getDate() { return date; }
    public String getPeriod() { return period; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}
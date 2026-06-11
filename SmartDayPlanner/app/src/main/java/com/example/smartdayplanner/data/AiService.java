package com.example.smartdayplanner.data;

import com.example.smartdayplanner.BuildConfig;
import com.example.smartdayplanner.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AiService {
    private static final String API_KEY = BuildConfig.GROQ_API_KEY;
    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private final OkHttpClient client;
    private final Gson gson;

    public interface AiCallback {
        void onSuccess(Map<String, List<Task>> tasksByDate);
        void onError(String error);
    }

    public AiService() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public void generatePlan(String userPrompt, List<Task> currentTasks, AiCallback callback) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String today = sdf.format(Calendar.getInstance().getTime());

        // Mevcut görevleri sıkıştırılmış formatta hazırla (Sadece çakışma ve tekrar kontrolü için)
        List<String> compressedTasks = new ArrayList<>();
        for (Task t : currentTasks) {
            compressedTasks.add(String.format("%s|%s|%s", t.getName(), t.getDate(), t.getPeriod()));
        }

        String systemPrompt = "Sen bir akıllı günlük planlayıcısın. Kullanıcının talebine göre ilgili günlerin planını güncelle.\n" +
                "BUGÜNÜN TARİHİ: " + today + "\n" +
                "STRATEJİ:\n" +
                "1. Sadece JSON döndür. Format: {\"dd/MM/yyyy\": [{\"name\": \"...\", \"durationMinutes\": 60, \"priority\": \"Yüksek\", \"date\": \"dd/MM/yyyy\", \"period\": \"Sabah\", \"isCompleted\": false}, ...]}\n" +
                "2. Sadece değişikliğin yapılacağı tarihleri anahtar (key) olarak kullan.\n" +
                "3. Bir tarih için liste oluştururken, o günün TÜM planını (varsa eski görevler + yeni eklenenler) içine koy. Liste dışındakiler o gün için silinecektir.\n" +
                "4. Mevcut Görevler: " + compressedTasks.toString() + "\n" +
                "5. Tarih 'dd/MM/yyyy', vakit 'Sabah/Öğle/Akşam' olmalıdır.";

        String context = "KULLANICI TALEBİ: " + userPrompt;

        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", systemPrompt));
        messages.add(new Message("user", context));

        ChatRequest chatRequest = new ChatRequest("llama-3.3-70b-versatile", messages);
        String jsonRequest = gson.toJson(chatRequest);

        RequestBody body = RequestBody.create(jsonRequest, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .header("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onError("API Hatası: " + response.code());
                    return;
                }

                String responseBody = response.body().string();
                try {
                    ChatResponse chatResponse = gson.fromJson(responseBody, ChatResponse.class);
                    String content = chatResponse.choices.get(0).message.content;
                    content = content.replace("```json", "").replace("```", "").trim();
                    
                    Map<String, List<Task>> rawResult = gson.fromJson(content, new TypeToken<Map<String, List<Task>>>(){}.getType());
                    Map<String, List<Task>> normalizedResult = new HashMap<>();

                    for (Map.Entry<String, List<Task>> entry : rawResult.entrySet()) {
                        List<Task> normalizedList = new ArrayList<>();
                        for (Task task : entry.getValue()) {
                            normalizedList.add(normalizeTask(task, entry.getKey()));
                        }
                        normalizedResult.put(entry.getKey(), normalizedList);
                    }
                    
                    callback.onSuccess(normalizedResult);
                } catch (Exception e) {
                    callback.onError("JSON Ayrıştırma Hatası: " + e.getMessage());
                }
            }
        });
    }

    private Task normalizeTask(Task task, String entryDate) {
        String period = task.getPeriod();
        if (period == null) period = "Sabah";
        if (period.equalsIgnoreCase("Afternoon") || period.equalsIgnoreCase("Öğlen")) period = "Öğle";
        if (period.equalsIgnoreCase("Morning")) period = "Sabah";
        if (period.equalsIgnoreCase("Evening") || period.equalsIgnoreCase("Night")) period = "Akşam";
        if (!period.equals("Sabah") && !period.equals("Öğle") && !period.equals("Akşam")) period = "Sabah";

        String date = task.getDate();
        if (date == null || date.isEmpty()) date = entryDate;

        if (date != null && date.contains("/")) {
            String[] parts = date.split("/");
            if (parts.length == 3) {
                String day = parts[0].length() == 1 ? "0" + parts[0] : parts[0];
                String month = parts[1].length() == 1 ? "0" + parts[1] : parts[1];
                date = day + "/" + month + "/" + parts[2];
            }
        }

        return new Task(task.getName(), task.getDurationMinutes(), task.getPriority(), date, period, task.isCompleted());
    }

    private static class ChatRequest {
        String model;
        List<Message> messages;
        ChatRequest(String model, List<Message> messages) {
            this.model = model;
            this.messages = messages;
        }
    }

    private static class Message {
        String role;
        String content;
        Message(String role, String content) {
            this.role = role;
            this.content = content;
        }
    }

    private static class ChatResponse {
        List<Choice> choices;
    }

    private static class Choice {
        Message message;
    }
}
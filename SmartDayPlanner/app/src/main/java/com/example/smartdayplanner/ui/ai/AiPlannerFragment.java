package com.example.smartdayplanner.ui.ai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.smartdayplanner.R;
import com.example.smartdayplanner.data.AiService;
import com.example.smartdayplanner.data.TaskPreferences;
import com.example.smartdayplanner.databinding.FragmentAiPlannerBinding;
import com.example.smartdayplanner.model.Task;

import java.util.List;
import java.util.Map;

public class AiPlannerFragment extends Fragment {

    private FragmentAiPlannerBinding binding;
    private AiService aiService;
    private TaskPreferences taskPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAiPlannerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        aiService = new AiService();
        taskPreferences = new TaskPreferences(requireContext());

        binding.btnGeneratePlan.setOnClickListener(v -> {
            String prompt = binding.editAiPrompt.getText().toString();
            if (prompt.isEmpty()) {
                Toast.makeText(getContext(), "Lütfen bir plan açıklaması girin", Toast.LENGTH_SHORT).show();
                return;
            }

            setLoading(true);
            List<Task> currentTasks = taskPreferences.getTasks();

            aiService.generatePlan(prompt, currentTasks, new AiService.AiCallback() {
                @Override
                public void onSuccess(Map<String, List<Task>> tasksByDate) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            setLoading(false);
                            if (tasksByDate != null && !tasksByDate.isEmpty()) {
                                taskPreferences.replaceTasksForDates(tasksByDate);
                                Toast.makeText(getContext(), "Plan güncellendi!", Toast.LENGTH_LONG).show();
                                Navigation.findNavController(binding.getRoot()).popBackStack();
                            } else {
                                Toast.makeText(getContext(), "AI yeni bir plan üretmedi.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

                @Override
                public void onError(String error) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            setLoading(false);
                            Toast.makeText(getContext(), "Hata: " + error, Toast.LENGTH_LONG).show();
                        });
                    }
                }
            });
        });

        return root;
    }

    private void setLoading(boolean isLoading) {
        binding.btnGeneratePlan.setEnabled(!isLoading);
        binding.progressAi.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
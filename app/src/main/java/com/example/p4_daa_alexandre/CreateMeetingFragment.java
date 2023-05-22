package com.example.p4_daa_alexandre;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.FragmentCreateMeetingBinding;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateMeetingFragment extends Fragment {

    private FragmentCreateMeetingBinding binding;
    private CreateMeetingViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        /**
         * instance de HomeFragmentViewModel
         */
        // Initialize the ViewModel with the MeetingRepository
        mViewModel = new ViewModelProvider(this, new ViewModelFactory()).get(CreateMeetingViewModel.class);
    }


    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateMeetingBinding.inflate(inflater, container, false);
        initCreateButton();
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void initCreateButton() {
        binding.createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = generateMeetingId();
                String title = binding.titleCreateMeetingInputedittext.getText().toString();
                // time Spiker - attention au format
                LocalTime time = LocalTime.parse(binding.hourCreateMeetingInputedittext.getText().toString());
                List<String> participants = Arrays.asList(binding.participantCreateMeetingInputedittext.getText().toString().split(", "));
                String roomName = binding.roomNameCreateMeetingInputedittext.getText().toString();

                // Ajouter une nouvelle réunion
                mViewModel.addMeeting(new Meeting(id, title, time, participants, roomName));

                // Retourner à la liste des réunions
                requireActivity().getSupportFragmentManager().popBackStack();
                Intent resultIntent = new Intent();
                requireActivity().setResult(Activity.RESULT_OK, resultIntent);
                // Peut fermer l'appli
                requireActivity().finish();
            }
        });
    }

    /**
     * Générer un nouveau ID
     */
    private int generateMeetingId() {
        return Math.abs(new Random().nextInt());
    }
}
package com.example.p4_daa_alexandre;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.FragmentCreateMeetingBinding;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateMeetingFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{

    private FragmentCreateMeetingBinding binding;
    private CreateMeetingViewModel mViewModel;
    private HomeFragment homeFragment;
    private int selectedHour;
    private int selectedMinute;

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
        initTimeButton();
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
                List<String> participants = Arrays.asList(binding.participantCreateMeetingInputedittext.getText().toString().split(", "));
                String roomName = binding.roomNameCreateMeetingInputedittext.getText().toString();

                // Vérifie que l'heure a été sélectionnée avant de créer la réunion
                if (selectedHour != 0 && selectedMinute != 0) {
                    // Crée un objet LocalTime à partir des valeurs sélectionnées
                    LocalTime time = LocalTime.of(selectedHour, selectedMinute);

                    // Ajoute une nouvelle réunion avec l'heure sélectionnée
                    mViewModel.addMeeting(new Meeting(id, title, time, participants, roomName));

                    // Retourner à la liste des réunions
                    requireActivity().getSupportFragmentManager().popBackStack();
                    Intent resultIntent = new Intent();
                    requireActivity().setResult(Activity.RESULT_OK, resultIntent);
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container_fragment, homeFragment);
                } else {
                    // Affiche un message d'erreur ou effectue une autre action appropriée si l'heure n'a pas été sélectionnée
                }
            }
        });
    }

    private void initTimeButton(){
        binding.chooseTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.setTargetFragment(CreateMeetingFragment.this, 0); // Définit le fragment actuel comme fragment cible
                timePicker.show(requireActivity().getSupportFragmentManager(), "time picker");
            }
        });
    }
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        binding.hourSelectedTextView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    public void onTimeSelected(int hourOfDay, int minute) {
        selectedHour = hourOfDay;
        selectedMinute = minute;
        binding.hourSelectedTextView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    /**
     * Générer un nouveau ID
     */
    private int generateMeetingId() {
        return Math.abs(new Random().nextInt());
    }
}
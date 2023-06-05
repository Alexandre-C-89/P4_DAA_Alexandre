package com.example.p4_daa_alexandre;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.databinding.FragmentCreateMeetingBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CreateMeetingFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private FragmentCreateMeetingBinding binding;
    private CreateMeetingViewModel mViewModel;
    private HomeFragment homeFragment;

    private int selectedHour;
    private int selectedMinute;
    private int selectedDay = -1;
    private int selectedYear = -1;
    private int selectedMonth = -1;

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
        initDateButton();
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
                String title = binding.titleCreateMeetingInputedittext.getText().toString();
                List<String> participants = Arrays.asList(binding.participantCreateMeetingInputedittext.getText().toString().split(", "));
                String roomName = binding.roomNameCreateMeetingInputedittext.getText().toString();

                // Vérifie que l'heure a été sélectionnée avant de créer la réunion
                if (selectedHour != 0 && selectedMinute != 0 && selectedDay != -1 && selectedYear != -1) {
                    // Crée un objet LocalTime à partir des valeurs sélectionnées
                    LocalTime time = LocalTime.of(selectedHour, selectedMinute);
                    // Crée un objet LocalDate à partir des valeurs sélectionnées
                    LocalDate date = LocalDate.of(selectedYear, selectedMonth, selectedDay);

                    // Ajoute une nouvelle réunion avec l'heure sélectionnée
                    mViewModel.addMeeting(new Meeting(title, time, date, participants, roomName));

                    // Retourner à la liste des réunions
                    requireActivity().getSupportFragmentManager().popBackStackImmediate();
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

    private void initDateButton() {
        binding.chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), CreateMeetingFragment.this, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        selectedHour = hourOfDay;
        selectedMinute = minute;
        binding.hourSelectedTextView.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        selectedDay = dayOfMonth;
        selectedYear = year;
        selectedMonth = month + 1; // Ajoute 1 au mois sélectionné

        binding.dateSelectedTextView.setText("Day: " + dayOfMonth + " Year: " + year);
    }
}
package com.example.p4_daa_alexandre.data.meeting;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.ui.home.HomeFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>();

    @NonNull
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    public void addMeeting(Meeting meeting) {
        List<Meeting> currentList = meetingsLiveData.getValue();

        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        currentList.add(meeting);

        meetingsLiveData.setValue(currentList);
    }

    public void deleteMeeting(int meetingId) {
        List<Meeting> currentList = meetingsLiveData.getValue();

        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.remove(meetingId);
        meetingsLiveData.setValue(currentList);
    }

    /**
     * Méthode filter
     */
    public void filterDay(List<Meeting> meetings) {
        // Obtenir la date d'aujourd'hui en tant que LocalDate
        LocalDate today = LocalDate.now();

        // Filtrer les réunions par la date d'aujourd'hui
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (Meeting meeting : meetings) {
            LocalDate meetingDate = meeting.getDate();
            Log.d("Date", "Meeting date: " + meetingDate);
            Log.d("Date", "Today's date: " + today);

            if (isSameDay(meetingDate, today)) {
                filteredMeetings.add(meeting);
            }
        }

        // Mettre à jour la liste des réunions dans le fragment HomeFragment
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        homeFragment.updateFilterList(filteredMeetings);
    }
}

package com.example.p4_daa_alexandre.data.meeting;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

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
    private boolean isSameDay(LocalDate date1, LocalDate date2) {
        return date1.isEqual(date2);
    }

    public LiveData<List<Meeting>> getFilteredMeetingsLiveData() {
        return Transformations.map(meetingsLiveData, new Function<List<Meeting>, List<Meeting>>() {
            @Override
            public List<Meeting> apply(List<Meeting> meetings) {
                // Filtrer les réunions par la date d'aujourd'hui
                LocalDate today = LocalDate.now();
                List<Meeting> filteredMeetings = new ArrayList<>();
                for (Meeting meeting : meetings) {
                    LocalDate meetingDate = meeting.getDate();
                    if (isSameDay(meetingDate, today)) {
                        filteredMeetings.add(meeting);
                    }
                }
                return filteredMeetings;
            }
        });
    }

    public LiveData<List<Meeting>> getFilteredMeetingsByRoomLiveData(final int roomId) {
        return Transformations.map(meetingsLiveData, new Function<List<Meeting>, List<Meeting>>() {
            @Override
            public List<Meeting> apply(List<Meeting> meetings) {
                List<Meeting> filteredMeetings = new ArrayList<>();
                for (Meeting meeting : meetings) {
                    if (meeting.getRoomId() == roomId) {
                        filteredMeetings.add(meeting);
                    }
                }
                return filteredMeetings;
            }
        });
    }

}

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
    public void filterMeetingsByDate(LocalDate selectedDate) {
        List<Meeting> allMeetings = meetingsLiveData.getValue();

        if (allMeetings != null && selectedDate != null) {
            List<Meeting> filteredMeetings = new ArrayList<>();
            for (Meeting meeting : allMeetings) {
                if (meeting.getDate().isEqual(selectedDate)) {
                    filteredMeetings.add(meeting);
                }
            }
            meetingsLiveData.setValue(filteredMeetings);
        }
    }

    public LiveData<List<Meeting>> getFilteredMeetingsByRoomLiveData(final String roomName) {
        return Transformations.map(meetingsLiveData, new Function<List<Meeting>, List<Meeting>>() {
            @Override
            public List<Meeting> apply(List<Meeting> meetings) {
                List<Meeting> filteredMeetings = new ArrayList<>();
                for (Meeting meeting : meetings) {
                    if (meeting.getRoomName().equals(roomName)) {
                        filteredMeetings.add(meeting);
                    }
                }
                return filteredMeetings;
            }
        });
    }

}

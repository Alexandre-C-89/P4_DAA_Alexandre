package com.example.p4_daa_alexandre.data.meeting;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

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
}

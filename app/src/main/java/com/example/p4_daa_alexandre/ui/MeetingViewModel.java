package com.example.p4_daa_alexandre.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.List;

public class MeetingViewModel extends ViewModel {
    private MeetingRepository meetingRepository;
    private LiveData<List<Meeting>> filteredMeetingsLiveData;

    public MeetingViewModel() {
        meetingRepository = new MeetingRepository();
        filteredMeetingsLiveData = meetingRepository.getFilteredMeetingsLiveData();
    }

    public LiveData<List<Meeting>> getFilteredMeetingsLiveData() {
        return filteredMeetingsLiveData;
    }

    public void addMeeting(Meeting meeting) {
        meetingRepository.addMeeting(meeting);
    }

    public void deleteMeeting(int meetingId) {
        meetingRepository.deleteMeeting(meetingId);
    }
}
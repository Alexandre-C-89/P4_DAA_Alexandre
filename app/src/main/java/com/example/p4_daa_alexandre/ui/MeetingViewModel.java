package com.example.p4_daa_alexandre.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.List;

public class MeetingViewModel extends ViewModel {
    private MeetingRepository meetingRepository;
    private LiveData<List<Meeting>> filteredMeetingsLiveData;
    private LiveData<List<Meeting>> filteredMeetingsByRoomLiveData;

    public MeetingViewModel() {
        meetingRepository = new MeetingRepository();
        filteredMeetingsLiveData = meetingRepository.getFilteredMeetingsLiveData();
        filteredMeetingsByRoomLiveData = meetingRepository.getFilteredMeetingsByRoomLiveData();
    }

    public LiveData<List<Meeting>> getFilteredMeetingsLiveData() {
        return filteredMeetingsLiveData;
    }

    /**
     * Reset du filtre
     * @return
     */
    public LiveData<List<Meeting>> getResetFilter() {
        return meetingRepository.getMeetingsLiveData();
    }

    public LiveData<List<Meeting>> getFilteredMeetingsByRoomLiveData() {
        return filteredMeetingsByRoomLiveData;
    }
}
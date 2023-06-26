package com.example.p4_daa_alexandre.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalDate;
import java.util.List;

public class MeetingViewModel extends ViewModel {
    private final MeetingRepository meetingRepository;
    private final MutableLiveData<List<Meeting>> filteredMeetingsLiveData = new MutableLiveData<>();

    public MeetingViewModel() {
        meetingRepository = new MeetingRepository();
    }

    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingRepository.getMeetingsLiveData();
    }

    public LiveData<List<Meeting>> getFilteredMeetingsLiveData() {
        return filteredMeetingsLiveData;
    }

    public void filterMeetingsByDate(LocalDate selectedDate) {
        meetingRepository.filterMeetingsByDate(selectedDate);
    }

    public void resetFilter() {
        filteredMeetingsLiveData.setValue(meetingRepository.getMeetingsLiveData().getValue());
    }
}
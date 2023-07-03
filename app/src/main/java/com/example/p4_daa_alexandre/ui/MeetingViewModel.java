package com.example.p4_daa_alexandre.ui;

import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.DI.Di;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;

import java.time.LocalDate;

public class MeetingViewModel extends ViewModel {
    private final MeetingRepository meetingRepository;

    public MeetingViewModel() {
        meetingRepository = Di.getMeetingRepository();
    }

    public void filterMeetingsByDate(LocalDate selectedDate) {
        meetingRepository.filterMeetingsByDate(selectedDate);
    }

    public void filterMeetingsByRoomName(String roomName) {
        meetingRepository.getFilteredMeetingsByRoom(roomName);
    }
    public void updateListBasedOnSearchText(String newRoomName) {
        meetingRepository.getFilteredMeetingsByRoom(newRoomName);
    }

    public void resetFilter() {
        meetingRepository.resetFilter();
    }
}
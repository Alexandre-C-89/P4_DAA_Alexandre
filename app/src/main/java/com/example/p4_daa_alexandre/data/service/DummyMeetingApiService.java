package com.example.p4_daa_alexandre.data.service;

import com.example.p4_daa_alexandre.data.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetingList = DummyMeetingGenerator.generateMeeting();

    @Override
    public List<Meeting> getMeeting() {
        return meetingList;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingList.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }

    @Override
    public void getFromDate(String date) {

    }

    @Override
    public void getFromPlace(String place) {

    }

}

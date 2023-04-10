package com.example.p4_daa_alexandre.data.service;

import com.example.p4_daa_alexandre.data.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Create method CRUD
     * @return
     */

    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    void getFromDate(String date);

    void getFromPlace(String place);

}

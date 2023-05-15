package com.example.p4_daa_alexandre.DI;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;

public class Di {

    private static MeetingRepository meetingRepository = new MeetingRepository();

    public static MeetingRepository getMeetingRepository() {
        return meetingRepository;
    }

}

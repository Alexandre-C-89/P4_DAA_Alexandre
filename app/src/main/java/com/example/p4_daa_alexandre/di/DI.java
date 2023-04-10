package com.example.p4_daa_alexandre.di;

import com.example.p4_daa_alexandre.data.service.DummyMeetingApiService;
import com.example.p4_daa_alexandre.data.service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }

}

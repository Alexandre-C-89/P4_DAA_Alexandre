package com.example.p4_daa_alexandre.di;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MeetingRepository service = new MeetingRepository();

    /**
     * Get an instance on @{@link MeetingRepository}
     * @return
     */
    public static MeetingRepository getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingRepository}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingRepository getNewInstanceApiService() {
        return new MeetingRepository();
    }
}

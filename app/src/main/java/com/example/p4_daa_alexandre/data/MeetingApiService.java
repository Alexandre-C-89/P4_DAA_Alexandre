package com.example.p4_daa_alexandre.data;

import java.util.List;

public abstract interface MeetingApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Meeting> getMeetingLists();

    /**
     * Deletes a neighbour
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     *
     */
    Meeting getFromId(long id);

}

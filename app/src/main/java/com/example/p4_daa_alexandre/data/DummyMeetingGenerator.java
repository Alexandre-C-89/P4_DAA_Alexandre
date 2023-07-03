package com.example.p4_daa_alexandre.data;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting( "Caroline", LocalTime.of(10, 30), LocalDate.of(2023, 10, 2), Arrays.asList("joe", "Alex"),
                    "Salle 1")
            );

    public static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

}

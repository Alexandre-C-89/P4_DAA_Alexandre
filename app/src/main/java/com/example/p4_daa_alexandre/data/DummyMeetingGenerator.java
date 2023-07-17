package com.example.p4_daa_alexandre.data;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting( "Caroline", LocalTime.of(10, 30), LocalDate.of(2023, 2, 2), Arrays.asList("joe", "Alex"),
                    "Salle 1"),
            new Meeting( "Demo Android", LocalTime.of(10, 30), LocalDate.of(2023, 9, 2), Arrays.asList("Antoine", "Anthony"),
                    "Salle 4"),
            new Meeting( "Demo Design", LocalTime.of(10, 30), LocalDate.of(2023, 10, 17), Arrays.asList("joe", "David", "Camille", "Viviane"),
                    "Salle 5"),
            new Meeting( "Meeting all", LocalTime.of(10, 30), LocalDate.of(2023, 10, 2), Arrays.asList("joe", "Alex", "David", "Camille", "Viviane", "Antoine", "Anthony"),
                    "Salle 2")
    );


    public static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

}

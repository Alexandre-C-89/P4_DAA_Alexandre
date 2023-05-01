package com.example.p4_daa_alexandre.data;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(1, "Caroline", LocalTime.of(10, 30), Arrays.asList("joe", "Alex"),
                    "Metallica")
            );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

}

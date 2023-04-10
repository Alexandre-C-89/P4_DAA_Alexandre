package com.example.p4_daa_alexandre.data.service;

import com.example.p4_daa_alexandre.data.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(
            new Meeting(0,"Point OnBoarding", 10, "salle washington", "Point sur la parti Design et dev des écrans inscription.", "anthony@lamzone.com, jennifer@lamzone.com, alfred@lamzone.com")
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }

}

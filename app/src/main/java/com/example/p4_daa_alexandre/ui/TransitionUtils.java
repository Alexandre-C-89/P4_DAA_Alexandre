package com.example.p4_daa_alexandre.ui;

public class TransitionUtils {

    public static String getMeetingRoomTransitionName(int meetingId) {
        return "meeting_room" + meetingId;
    }

    public static String getMeetingTopicTransitionName(int meetingId) {
        return "meeting_topic" + meetingId;
    }

}

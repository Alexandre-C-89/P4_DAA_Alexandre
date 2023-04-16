package com.example.p4_daa_alexandre.data;

import androidx.annotation.NonNull;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Meeting {

    private final int id;

    @NonNull
    private final String topic;

    @NonNull
    private final LocalTime time;

    @NonNull
    private final List<String> participants;

    @NonNull
    private final String roomName;

    public Meeting(
            int id,
            @NonNull String topic,
            @NonNull LocalTime time,
            @NonNull List<String> participants,
            @NonNull String roomName
    ) {
        this.id = id;
        this.topic = topic;
        this.time = time;
        this.participants = participants;
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTopic() {
        return topic;
    }

    @NonNull
    public LocalTime getTime() {
        return time;
    }

    @NonNull
    public List<String> getParticipants() {
        return participants;
    }

    @NonNull
    public String getRoomName() {
        return roomName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id == meeting.id &&
                topic.equals(meeting.topic) &&
                time.equals(meeting.time) &&
                participants.equals(meeting.participants) &&
                roomName == meeting.roomName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, topic, time, participants, roomName);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", time=" + time +
                ", participants=" + participants +
                ", room=" + roomName +
                '}';
    }

}

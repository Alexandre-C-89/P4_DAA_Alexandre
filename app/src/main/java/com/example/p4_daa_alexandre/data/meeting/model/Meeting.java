package com.example.p4_daa_alexandre.data.meeting.model;

import androidx.annotation.NonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Meeting {

    private final int id = 0;

    @NonNull
    private final String title;

    @NonNull
    private final LocalTime time;

    @NonNull
    private final LocalDate date;

    @NonNull
    private final List<String> participants;

    @NonNull
    private final String roomName;

    public Meeting(
            @NonNull String title,
            @NonNull LocalTime time,
            @NonNull LocalDate date,
            @NonNull List<String> participants,
            @NonNull String roomName
    ) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.participants = participants;
        this.roomName = roomName;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public LocalTime getTime() {
        return time;
    }
    @NonNull
    public LocalDate getDate() {
        return date;
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
                title.equals(meeting.title) &&
                time.equals(meeting.time) &&
                date.equals(meeting.date) &&
                participants.equals(meeting.participants) &&
                roomName == meeting.roomName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, participants, roomName);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", topic='" + title + '\'' +
                ", time=" + time +
                ", date=" + date +
                ", participants=" + participants +
                ", room=" + roomName +
                '}';
    }

}

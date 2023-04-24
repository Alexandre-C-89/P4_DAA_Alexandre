package com.example.p4_daa_alexandre.ui.details;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import java.util.List;
import java.util.Objects;

public class MeetingDetailViewState {

    @DrawableRes
    private final int meetingIcon;

    @NonNull
    private final String title;

    @NonNull
    private final List<Participant> participants;

    @NonNull
    private final String scheduleMessage;

    public MeetingDetailViewState(
            @DrawableRes int meetingIcon,
            @NonNull String title,
            @NonNull List<Participant> participants,
            @NonNull String scheduleMessage
    ) {
        this.meetingIcon = meetingIcon;
        this.title = title;
        this.participants = participants;
        this.scheduleMessage = scheduleMessage;
    }

    @DrawableRes
    public int getMeetingIcon() {
        return meetingIcon;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public List<Participant> getParticipants() {
        return participants;
    }

    @NonNull
    public String getScheduleMessage() {
        return scheduleMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingDetailViewState that = (MeetingDetailViewState) o;
        return meetingIcon == that.meetingIcon &&
                title.equals(that.title) &&
                participants.equals(that.participants) &&
                scheduleMessage.equals(that.scheduleMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingIcon, title, participants, scheduleMessage);
    }

    @NonNull
    @Override
    public String toString() {
        return "MeetingDetailViewState{" +
                "meetingIcon=" + meetingIcon +
                ", ='" + title + '\'' +
                ", participants=" + participants +
                ", scheduleMessage='" + scheduleMessage + '\'' +
                '}';
    }

    public static class Participant {

        @NonNull
        private final String name;

        @NonNull
        private final String participantUrl;

        public Participant(@NonNull String name, @NonNull String participantUrl) {
            this.name = name;
            this.participantUrl = participantUrl;
        }

        @NonNull
        public String getName() {
            return name;
        }

        @NonNull
        public String getParticipantUrl() {
            return participantUrl;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Participant that = (Participant) o;
            return name.equals(that.name) &&
                    participantUrl.equals(that.participantUrl);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, participantUrl);
        }

        @NonNull
        @Override
        public String toString() {
            return "Participant{" +
                    "name='" + name + '\'' +
                    ", participantUrl='" + participantUrl + '\'' +
                    '}';
        }
    }
}

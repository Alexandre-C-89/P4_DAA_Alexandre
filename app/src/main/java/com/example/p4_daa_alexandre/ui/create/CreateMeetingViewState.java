package com.example.p4_daa_alexandre.ui.create;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CreateMeetingViewState {

    @NonNull
    private final String rooms;

    @NonNull
    private final String time;

    @Nullable
    private final String topicError;

    @Nullable
    private final String participantsError;

    @Nullable
    private final String roomError;

    public CreateMeetingViewState(
            @NonNull String rooms,
            @NonNull String time,
            @Nullable String topicError,
            @Nullable String participantsError,
            @Nullable String roomError
    ) {
        this.rooms = rooms;
        this.time = time;
        this.topicError = topicError;
        this.participantsError = participantsError;
        this.roomError = roomError;
    }

    @NonNull
    public String getRooms() {
        return rooms;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    @Nullable
    public String getTopicError() {
        return topicError;
    }

    @Nullable
    public String getParticipantsError() {
        return participantsError;
    }

    @Nullable
    public String getRoomError() {
        return roomError;
    }

}

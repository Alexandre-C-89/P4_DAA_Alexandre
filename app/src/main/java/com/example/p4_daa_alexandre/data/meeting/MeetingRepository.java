package com.example.p4_daa_alexandre.data.meeting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.example.p4_daa_alexandre.config.BuildConfigResolver;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());

    private long maxId = 0;

    public MeetingRepository(BuildConfigResolver buildConfigResolver) {
        // At startup, when creating repo, if we're in debug mode, add random Neighbours
        if (buildConfigResolver.isDebug()) {
            generateRandomMeetings();
        }
    }

    public void addMeeting(
            @NonNull String title,
            @NonNull List<String> participants,
            @Nullable String roomName
    ) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) return;

        meetings.add(
                new Meeting(
                        (int) maxId++,
                        title,
                        participants,
                        roomName
                )
        );

        meetingsLiveData.setValue(meetings);
    }

    public void deleteMeeting(long meetingId) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null) return;

        for (Iterator<Meeting> iterator = meetings.iterator(); iterator.hasNext(); ) {
            Meeting meeting = iterator.next();

            if (meeting.getId() == meetingId) {
                iterator.remove();
                break;
            }
        }

        meetingsLiveData.setValue(meetings);
    }

    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    public LiveData<Meeting> getMeetingLiveData(long meetingId) {
        // We use a Transformation here so whenever the neighboursLiveData changes, the underlying lambda will be called too, and
        // the Neighbour will be re-emitted (with potentially new information like isFavorite set to true or false)

        // This Transformation transforms a List of Neighbours into a Neighbour (matched by its ID)
        return Transformations.map(meetingsLiveData, meetings -> {
            for (Meeting meeting : meetings) {
                if (meeting.getId() == meetingId) {
                    return meeting;
                }
            }

            return null;
        });
    }

    private void generateRandomMeetings() {
        addMeeting(
                "Caroline",
                Arrays.asList("Joe", "Alex"),
                "Metallica"
        );
    }
}

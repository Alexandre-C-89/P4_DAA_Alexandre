package com.example.p4_daa_alexandre.data.meeting;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.p4_daa_alexandre.BuildConfig;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.utils.debug.Mock;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>();

    private int highestMeetingId = 0;

    public void addMeeting(
            @NonNull String topic,
            @NonNull LocalTime time,
            @NonNull List<String> participants,
            //@NonNull Room room
            @NonNull String room
    ) {
        List<Meeting> currentList = meetingsLiveData.getValue();

        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        currentList.add(
                new Meeting(
                        highestMeetingId,
                        topic,
                        time,
                        participants,
                        room
                )
        );

        highestMeetingId++;

        meetingsLiveData.setValue(currentList);
    }

    public void deleteMeeting(int meetingId) {
        List<Meeting> currentList = meetingsLiveData.getValue();

        if (currentList == null) {
            currentList = new ArrayList<>();
        }

        for (Iterator<Meeting> iterator = currentList.iterator(); iterator.hasNext(); ) {
            Meeting meeting = iterator.next();

            if (meeting.getId() == meetingId) {
                iterator.remove();
                break;
            }
        }

        meetingsLiveData.setValue(currentList);
    }

    @NonNull
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    // DEBUG
    public void addDebugMeeting() {
        if (BuildConfig.DEBUG) {
            addMeeting(
                    Mock.getRandomMeetingTopic(),
                    Mock.getRandomMeetingHour(),
                    Mock.getRandomMeetingParticipants(),
                    Mock.getRandomMeetingRoom()
            );
        }
    }
}

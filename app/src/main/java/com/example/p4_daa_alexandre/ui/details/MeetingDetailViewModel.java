package com.example.p4_daa_alexandre.ui.details;

import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MINUTES;
import android.app.Application;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.R;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;
import com.example.p4_daa_alexandre.utils.livedata.SingleLiveEvent;

import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MeetingDetailViewModel extends ViewModel {

    @NonNull
    private final Application application;

    @NonNull
    private final Resources resources;

    @NonNull
    private final MeetingRepository meetingRepository;

    @NonNull
    private final Clock clock;

    private final MediatorLiveData<MeetingDetailViewState> viewStateMediatorLiveData = new MediatorLiveData<>();

    private final SingleLiveEvent<MeetingDetailViewAction> viewActionSingleLiveEvent = new SingleLiveEvent<>();

    public MeetingDetailViewModel(
            @NonNull Application application,
            @NonNull Resources resources,
            @NonNull MeetingRepository meetingRepository,
            @NonNull Clock clock
    ) {
        this.application = application;
        this.resources = resources;
        this.meetingRepository = meetingRepository;
        this.clock = clock;
    }

    public void init(int meetingId) {
        viewStateMediatorLiveData.addSource(meetingRepository.getMeetingsLiveData(), meetings -> {
            Meeting found = null;

            for (Meeting meeting : meetings) {
                if (meeting.getId() == meetingId) {
                    found = meeting;
                    break;
                }
            }

            if (found != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    viewStateMediatorLiveData.setValue(map(found));
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private MeetingDetailViewState map(@NonNull Meeting meeting) {
        List<MeetingDetailViewState.Participant> participants = new ArrayList<>();

        for (String participantUrl : meeting.getParticipants()) {
            String name;

            int indexOfAtSign = participantUrl.indexOf('@');

            if (indexOfAtSign != -1) {
                name = participantUrl.substring(0, indexOfAtSign);
            } else {
                name = participantUrl;
            }

            participants.add(new MeetingDetailViewState.Participant(name, participantUrl));
        }

        LocalTime now = LocalTime.now(clock);

        String message = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (now.isAfter(meeting.getTime().plusHours(1))) {
                message = resources.getString(R.string.meeting_finished);
            } else if (now.isAfter(meeting.getTime())) {
                message = resources.getString(R.string.meeting_ongoing);
            } else {
                long deltaHour = HOURS.between(now, meeting.getTime());

                if (deltaHour >= 1) {
                    message = resources.getQuantityString(R.plurals.meeting_in_hours, (int) deltaHour, deltaHour);
                } else {
                    long deltaMinutes = MINUTES.between(now, meeting.getTime());

                    message = resources.getQuantityString(R.plurals.meeting_in_minutes, (int) deltaMinutes, deltaMinutes);
                }
            }
        }

        return new MeetingDetailViewState(
                meeting.getId(),
                meeting.getTitle(),
                participants,
                message
        );
    }

    @NonNull
    public LiveData<MeetingDetailViewState> getViewStateLiveData() {
        return viewStateMediatorLiveData;
    }

    @NonNull
    public SingleLiveEvent<MeetingDetailViewAction> getViewActionSingleLiveEvent() {
        return viewActionSingleLiveEvent;
    }

    public void onParticipantClicked(MeetingDetailViewState.Participant participant) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{participant.getParticipantUrl()});
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        viewActionSingleLiveEvent.setValue(new MeetingDetailViewAction.LaunchIntent(intent));
    }

    public static abstract class MeetingDetailViewAction {

        public static class LaunchIntent extends MeetingDetailViewAction {

            @NonNull
            private final Intent intent;

            public LaunchIntent(@NonNull Intent intent) {
                this.intent = intent;
            }

            @NonNull
            public Intent getIntent() {
                return intent;
            }
        }
    }
}

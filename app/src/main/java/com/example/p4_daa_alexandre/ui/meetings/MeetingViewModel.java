package com.example.p4_daa_alexandre.ui.meetings;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.utils.livedata.SingleLiveEvent;

public class MeetingViewModel {

    @NonNull
    private final Resources resources;
    //private final MediatorLiveData<MeetingViewState> meetingViewStateMediatorLiveData = new MediatorLiveData<>();
    @NonNull
    private final MeetingRepository meetingRepository;

    // ViewAction : display sorting dialog
    private final SingleLiveEvent<MeetingViewAction> viewActionLiveEvent = new SingleLiveEvent<>();

    public MeetingViewModel(
            @NonNull final Resources resources,
            @NonNull final MeetingRepository meetingRepository
            //@NonNull final SortingParametersRepository sortingParametersRepository
    ) {
        this.resources = resources;
        this.meetingRepository = meetingRepository;

        //wireMeetingModelsMediator(sortingParametersRepository);
    }

    public void onAddDebugMeetingClicked() {
        meetingRepository.addDebugMeeting();
    }

    // region Meetings
    /* **********
     * MEETINGS *
     ********** */

    public SingleLiveEvent<MeetingViewAction> getViewActionSingleLiveEvent() {
        return viewActionLiveEvent;
    }

    public void onDeleteMeetingClicked(int meetingId) {
        meetingRepository.deleteMeeting(meetingId);
    }

}

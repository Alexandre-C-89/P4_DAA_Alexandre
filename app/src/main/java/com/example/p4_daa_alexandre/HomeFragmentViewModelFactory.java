package com.example.p4_daa_alexandre;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;

public class HomeFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final MeetingRepository mMeetingRepository;

    public HomeFragmentViewModelFactory(MeetingRepository meetingRepository) {
        mMeetingRepository = meetingRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeFragmentViewModel.class)) {
            return (T) new HomeFragmentViewModel(mMeetingRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

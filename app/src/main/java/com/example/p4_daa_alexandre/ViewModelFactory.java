package com.example.p4_daa_alexandre;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.p4_daa_alexandre.DI.Di;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MeetingRepository mMeetingRepository;

    public ViewModelFactory() {
        mMeetingRepository = Di.getMeetingRepository();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(mMeetingRepository);
        }
        if (modelClass.isAssignableFrom(CreateMeetingViewModel.class)) {
            return (T) new CreateMeetingViewModel(mMeetingRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

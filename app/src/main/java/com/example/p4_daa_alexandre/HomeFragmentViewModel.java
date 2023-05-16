package com.example.p4_daa_alexandre;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalTime;
import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

        // REPOSITORIES
        private final MeetingRepository mMeetingRepository;

        public HomeFragmentViewModel(MeetingRepository mMeetingRepository) {

            this.mMeetingRepository = mMeetingRepository;

        }

        // -------------

        // FOR MEETING

        // -------------

        public LiveData<List<Meeting>> getMeetings() { return mMeetingRepository.getMeetingsLiveData();  }

        // -------------

        // FOR ITEM

        // -------------

        public void addMeeting(int id, String title, LocalTime time, List<String> participants, String roomName) {

                mMeetingRepository.addMeeting(new Meeting(id, title, time, participants, roomName));
        }

        public void deleteMeeting(int id) {

            mMeetingRepository.deleteMeeting(id);

        }

}

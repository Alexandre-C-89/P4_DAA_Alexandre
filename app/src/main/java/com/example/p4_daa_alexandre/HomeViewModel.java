package com.example.p4_daa_alexandre;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.List;

public class HomeViewModel extends ViewModel {

        // REPOSITORIES
        private final MeetingRepository mMeetingRepository;

        public HomeViewModel(MeetingRepository mMeetingRepository) {

            this.mMeetingRepository = mMeetingRepository;

        }

        /**
         * Liste de meeting
         */
        public LiveData<List<Meeting>> getMeetings() { return mMeetingRepository.getMeetingsLiveData();  }

        /**
         * Création de meeting
         */

        public void addMeeting(Meeting meeting) {

                mMeetingRepository.addMeeting(meeting);
        }

        public void deleteMeeting(int id) {

            mMeetingRepository.deleteMeeting(id);

        }
}

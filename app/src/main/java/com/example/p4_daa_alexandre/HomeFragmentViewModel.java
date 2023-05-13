package com.example.p4_daa_alexandre;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.Executor;

public class HomeFragmentViewModel extends ViewModel {

    //constructor
        // REPOSITORIES

        private final MeetingRepository mMeetingRepository;

        private final Executor executor;

        // DATA

        @Nullable

        private LiveData<Meeting> currentMeetings;

        public HomeFragmentViewModel(MeetingRepository mMeetingRepository, Executor executor) {

            this.mMeetingRepository = mMeetingRepository;

            this.executor = executor;

        }

    public void init(int id) {

            if (this.mMeetingRepository != null) {

                return;

            }

            currentMeetings = mMeetingRepository.getMeetings(id);

        }

        // -------------

        // FOR MEETING

        // -------------

        public LiveData<Meeting> getMeetings() { return this.currentMeetings;  }

        // -------------

        // FOR ITEM

        // -------------

        public LiveData<List<Meeting>> getMeetings(int id) {

            return mMeetingRepository.getMeetings(id);

        }

        public void createMeeting(int id, String title, LocalTime time, List<String> participants, String roomName) {

            executor.execute(() -> {

                mMeetingRepository.createMeeting(new Meeting(id, title, time, participants, roomName));

            });

        }

        public void deleteMeeting(int id) {

            executor.execute(() -> mMeetingRepository.deleteMeeting(id));

        }

        public void updateMeeting(Meeting meeting) {

            executor.execute(() -> mMeetingRepository.updateMeeting(meeting));
        }

}

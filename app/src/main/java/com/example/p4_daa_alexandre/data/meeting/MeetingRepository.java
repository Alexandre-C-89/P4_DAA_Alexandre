package com.example.p4_daa_alexandre.data.meeting;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.p4_daa_alexandre.data.DummyMeetingGenerator;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>();

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeeting();

    public MeetingRepository(){
        meetingsLiveData.setValue(meetings);
    }

    @NonNull
    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
        meetingsLiveData.setValue(meetings);
    }

    public void deleteMeeting(int meetingId) {
        meetings.remove(meetingId);
        meetingsLiveData.setValue(meetings);
    }

    /**
     * Méthode filter par jour
     */
    public void filterMeetingsByDate(LocalDate selectedDate) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (Meeting meeting : meetings) {
            LocalDate meetingDate = meeting.getDate();
            if (meetingDate.isEqual(selectedDate)) {
                filteredMeetings.add(meeting);
            }
        }
        meetingsLiveData.setValue(filteredMeetings);
    }

    /**
     * Méthode filter par nom
     */
    public void getFilteredMeetingsByRoom(final String roomName) {
        List<Meeting> filteredMeetings = new ArrayList<>();
        for (Meeting meeting : meetings) {
            String meetingRoomName = meeting.getRoomName();
            if (meetingRoomName.equals(roomName)) {
                filteredMeetings.add(meeting);
                // SearchView affiche dans ma toolbar un edit Text
            }
        }
        meetingsLiveData.setValue(filteredMeetings);
    }

    private void updateListBasedOnSearchText(String newRoomName) {
        // Mettez à jour la liste en fonction du texte de recherche en temps réel
        // Vous pouvez implémenter cette méthode selon vos besoins
        // Par exemple, vous pouvez réappliquer le filtre de recherche à chaque modification du texte
        getFilteredMeetingsByRoom(newRoomName);
    }

    /**
     * Méthode filter par nom
     */
    public void resetFilter() {
        meetingsLiveData.setValue(meetings);
    }

}

package com.example.p4_daa_alexandre.data;

import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetingList = DummyMeetingGenerator.generateMeeting();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetingLists() {
        return meetingList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingList.remove(meeting);
    }

    /**
     * {@inheritDoc}
     * @param meeting
     */
    @Override
    public void createMeeting(Meeting meeting) {
        meetingList.add(meeting);
    }

    @Override
    public Meeting getFromId(long id) {
        // Parcourt la liste de voisins et renvoie celui qui correspond à l’id ou null si aucun trouvé
        for (Meeting n: meetingList) {
            if (id == n.getId()) {
                return n;
            }
        }
        return null;
    }

}


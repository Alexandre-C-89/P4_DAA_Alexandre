package com.example.p4_daa_alexandre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import com.example.p4_daa_alexandre.DI.Di;
import com.example.p4_daa_alexandre.data.DummyMeetingGenerator;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private MeetingRepository service;

    @Before
    public void setup() {
        service = Di.getMeetingRepository();
    }

    @Test
    public void getMeetingsWithSuccess() {
        MeetingRepository repository = Di.getMeetingRepository();
        List<Meeting> meetings = repository.getMeetingsLiveData().getValue();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.generateMeeting();
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**@Test
    public void deleteMeetingWithSuccess() {
        DummyMeetingGenerator meetingGenerator = new DummyMeetingGenerator();
        List<Meeting> meetings = meetingGenerator.generateMeeting();
        Meeting meetingToDelete = meetings.get(0); // Obtenez la première réunion de la liste
        service.deleteMeeting(meetingToDelete.getId()); // Utilisez l'identifiant de la réunion
        assertFalse(service.getMeetingsLiveData().getValue().contains(meetingToDelete));
    }*/

    @Test
    public void createMeetingSuccess() {
        //DummyMeetingGenerator meetingGenerator = new DummyMeetingGenerator();
        //List<Meeting> meetings = meetingGenerator.generateMeeting();
        Meeting meetingToAdd = new Meeting("Demo", LocalTime.of(12, 30), LocalDate.of(2031, 10, 1), Arrays.asList("Joe", "Thomas"), "Salle 2");
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetingsLiveData().getValue().contains(meetingToAdd));
    }


}
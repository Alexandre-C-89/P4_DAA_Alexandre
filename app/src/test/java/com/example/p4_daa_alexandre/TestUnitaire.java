package com.example.p4_daa_alexandre;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.p4_daa_alexandre.DI.Di;
import com.example.p4_daa_alexandre.data.DummyMeetingGenerator;
import com.example.p4_daa_alexandre.data.meeting.MeetingRepository;
import com.example.p4_daa_alexandre.data.meeting.model.Meeting;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Rule;
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
public class TestUnitaire {

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
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

    @Test
    public void deleteMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetingsLiveData().getValue();
        Meeting meetingToDelete = meetings.get(0); // Obtenez la première réunion de la liste
        service.deleteMeeting(meetingToDelete.getId()); // Utilisez l'identifiant de la réunion
        assertFalse(service.getMeetingsLiveData().getValue().contains(meetingToDelete));
    }

    @Test
    public void createMeetingSuccess() {
        Meeting meetingToAdd = new Meeting("Demo", LocalTime.of(12, 30), LocalDate.of(2031, 10, 1), Arrays.asList("Joe", "Thomas"), "Salle 2");
        service.addMeeting(meetingToAdd);
        List<Meeting> meetings = service.getMeetingsLiveData().getValue();
        assertTrue(meetings.contains(meetingToAdd));
    }

    /**
     * filtrer avec résultat et un sans
     */
    @Test
    public void filterMeetingsByDate() {
        // Définir une date de filtrage
        LocalDate filterDate1 = LocalDate.of(2023, 7, 17);
        LocalDate filterDate2 = LocalDate.of(2023, 7, 11);

        Meeting meetingToAdd = new Meeting("Demo Design", LocalTime.of(12, 30), LocalDate.of(2023, 7, 17), Arrays.asList("Joe", "Thomas", "Antoine", "David"), "Salle 2");

        // Ajouter la réunion à la liste de réunions
        service.addMeeting(meetingToAdd);

        // Appliquer le filtre par date
        service.filterMeetingsByDate(filterDate1);
        List<Meeting> filteredMeetings1 = service.getMeetingsLiveData().getValue();

        assertEquals(1, filteredMeetings1.size());

        // Vérifier si les réunions filtrées correspondent à la date de filtrage
        for (Meeting meeting : filteredMeetings1) {
            assertEquals(filterDate1, meeting.getDate());
        }

        service.filterMeetingsByDate(filterDate2);
        List<Meeting> filteredMeetings2 = service.getMeetingsLiveData().getValue();


        for (Meeting meeting : filteredMeetings2) {
            assertEquals(filterDate2, meeting.getDate());
        }
    }
}
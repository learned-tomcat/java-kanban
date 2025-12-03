import gym.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TimetableTest {

    @Test
    void testGetTrainingSessionsForDaySingleSession() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        TrainingSession session = new TrainingSession(
                group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0)
        );

        timetable.addNewTrainingSession(session);

        List<TrainingSession> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        List<TrainingSession> tuesday = timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY);

        assertEquals(1, monday.size());
        assertEquals(session, monday.get(0));

        assertTrue(tuesday.isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayMultipleSessions() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        Group adult = new Group("Акробатика для взрослых", Age.ADULT, 90);
        TrainingSession th20 = new TrainingSession(adult, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(20, 0));

        timetable.addNewTrainingSession(th20);

        Group child = new Group("Акробатика для детей", Age.CHILD, 60);
        TrainingSession mo13 = new TrainingSession(child, coach,
                DayOfWeek.MONDAY, new TimeOfDay(13, 0));
        TrainingSession th13 = new TrainingSession(child, coach,
                DayOfWeek.THURSDAY, new TimeOfDay(13, 0));
        TrainingSession sa10 = new TrainingSession(child, coach,
                DayOfWeek.SATURDAY, new TimeOfDay(10, 0));

        timetable.addNewTrainingSession(mo13);
        timetable.addNewTrainingSession(th13);
        timetable.addNewTrainingSession(sa10);

        List<TrainingSession> monday = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        assertEquals(1, monday.size());

        List<TrainingSession> thursday = timetable.getTrainingSessionsForDay(DayOfWeek.THURSDAY);
        assertEquals(2, thursday.size());
        assertEquals(th13, thursday.get(0));
        assertEquals(th20, thursday.get(1));

        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.TUESDAY).isEmpty());
    }

    @Test
    void testGetTrainingSessionsForDayAndTime() {
        Timetable timetable = new Timetable();

        Group group = new Group("Акробатика для детей", Age.CHILD, 60);
        Coach coach = new Coach("Васильев", "Николай", "Сергеевич");

        TrainingSession session = new TrainingSession(
                group, coach, DayOfWeek.MONDAY, new TimeOfDay(13, 0)
        );

        timetable.addNewTrainingSession(session);

        assertEquals(1, timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, new TimeOfDay(13, 0)).size());

        assertTrue(timetable.getTrainingSessionsForDayAndTime(
                DayOfWeek.MONDAY, new TimeOfDay(14, 0)).isEmpty());
    }
}


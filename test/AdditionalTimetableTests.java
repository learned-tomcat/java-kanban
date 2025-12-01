import gym.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AdditionalTimetableTests {

    @Test
    void testMultipleSessionsSameTime() {
        Timetable timetable = new Timetable();

        Group g = new Group("Test", Age.ADULT, 60);
        Coach c1 = new Coach("А", "А", "А");
        Coach c2 = new Coach("Б", "Б", "Б");

        TrainingSession s1 = new TrainingSession(g, c1,
                DayOfWeek.FRIDAY, new TimeOfDay(15, 0));
        TrainingSession s2 = new TrainingSession(g, c2,
                DayOfWeek.FRIDAY, new TimeOfDay(15, 0));

        timetable.addNewTrainingSession(s1);
        timetable.addNewTrainingSession(s2);

        List<TrainingSession> result =
                timetable.getTrainingSessionsForDayAndTime(DayOfWeek.FRIDAY, new TimeOfDay(15, 0));

        assertEquals(2, result.size());
    }

    @Test
    void testTimesAreSorted() {
        Timetable timetable = new Timetable();

        Group g = new Group("X", Age.CHILD, 30);
        Coach c = new Coach("Z", "Z", "Z");

        timetable.addNewTrainingSession(new TrainingSession(g, c,
                DayOfWeek.WEDNESDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g, c,
                DayOfWeek.WEDNESDAY, new TimeOfDay(10, 0)));

        List<TrainingSession> list = timetable.getTrainingSessionsForDay(DayOfWeek.WEDNESDAY);

        assertEquals(10, list.get(0).getTimeOfDay().getHours());
        assertEquals(18, list.get(1).getTimeOfDay().getHours());
    }

    @Test
    void testEmptyDay() {
        Timetable timetable = new Timetable();

        assertTrue(timetable.getTrainingSessionsForDay(DayOfWeek.SUNDAY).isEmpty());
    }
}

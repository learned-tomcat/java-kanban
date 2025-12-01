import gym.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TimetableCountTest {

    @Test
    void testSingleCoachCount() {
        Timetable timetable = new Timetable();

        Coach coach = new Coach("Иванов", "Петр", "Иванович");
        Group group = new Group("Дети", Age.CHILD, 60);

        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group, coach,
                DayOfWeek.TUESDAY, new TimeOfDay(11, 0)));

        List<CounterOfTrainings> counts = timetable.getCountByCoaches();

        assertEquals(1, counts.size());
        assertEquals(2, counts.get(0).getCount());
    }

    @Test
    void testMultipleCoachesOrdering() {
        Timetable timetable = new Timetable();

        Coach a = new Coach("А", "А", "А");
        Coach b = new Coach("Б", "Б", "Б");
        Group g = new Group("Груп", Age.ADULT, 90);

        timetable.addNewTrainingSession(new TrainingSession(g, a,
                DayOfWeek.MONDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g, b,
                DayOfWeek.MONDAY, new TimeOfDay(11, 0)));
        timetable.addNewTrainingSession(new TrainingSession(g, b,
                DayOfWeek.MONDAY, new TimeOfDay(12, 0)));

        List<CounterOfTrainings> list = timetable.getCountByCoaches();

        assertEquals(b, list.get(0).getCoach());
        assertEquals(a, list.get(1).getCoach());
    }

    @Test
    void testZeroTrainings() {
        Timetable timetable = new Timetable();

        assertTrue(timetable.getCountByCoaches().isEmpty());
    }
}
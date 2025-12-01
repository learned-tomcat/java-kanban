package gym;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Coach coach1 = new Coach("Смирнов", "Кирилл", "Александрович");
        Coach coach2 = new Coach("Матвеев", "Антон", "Резалин");
        Coach coach3 = new Coach("Павлов", "Василий", "Иванов");

        Group group1 = new Group("Йога", Age.ADULT, 60);
        Group group2 = new Group("Художественная гимнатсика", Age.CHILD, 45);
        Group group3 = new Group("Аэробика", Age.ADULT, 50);

        Timetable timetable = new Timetable();

        timetable.addNewTrainingSession(new TrainingSession(group1, coach1, DayOfWeek.MONDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, coach2, DayOfWeek.MONDAY, new TimeOfDay(17, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group3, coach1, DayOfWeek.WEDNESDAY, new TimeOfDay(19, 30)));
        timetable.addNewTrainingSession(new TrainingSession(group1, coach1, DayOfWeek.FRIDAY, new TimeOfDay(18, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group2, coach2, DayOfWeek.SATURDAY, new TimeOfDay(10, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group3, coach1, DayOfWeek.SATURDAY, new TimeOfDay(11, 0)));
        timetable.addNewTrainingSession(new TrainingSession(group3, coach3, DayOfWeek.MONDAY, new TimeOfDay(21, 0)));
        System.out.println("Расписание на понедельник: ");
        List<TrainingSession> mondaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.MONDAY);
        for (TrainingSession session : mondaySessions) {
            System.out.printf("%s - %s (%s)\n",
                    session.getTimeOfDay(),
                    session.getGroup().getTitle(),
                    session.getCoach().getSurname());
        }

        System.out.println("\nРасписание на субботу: ");
        List<TrainingSession> saturdaySessions = timetable.getTrainingSessionsForDay(DayOfWeek.SATURDAY);
        for (TrainingSession session : saturdaySessions) {
            System.out.printf("%s - %s (%s)\n",
                    session.getTimeOfDay(),
                    session.getGroup().getTitle(),
                    session.getCoach().getSurname());
        }

        System.out.println("\nКоличество тренировок по тренерам: ");
        List<CounterOfTrainings> counters = timetable.getCountByCoaches();
        for (CounterOfTrainings counter : counters) {
            System.out.printf("%s %s %s: %d\n",
                    counter.getCoach().getSurname(),
                    counter.getCoach().getName(),
                    counter.getCoach().getMiddleName(),
                    counter.getCount());
        }
    }
}

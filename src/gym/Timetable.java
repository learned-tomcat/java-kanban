package gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable =
            new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {

        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        timetable.putIfAbsent(day, new TreeMap<>());

        TreeMap<TimeOfDay, List<TrainingSession>> mapForDay = timetable.get(day);
        mapForDay.putIfAbsent(time, new ArrayList<>());
        mapForDay.get(time).add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> map = timetable.get(dayOfWeek);
        if (map == null) return List.of();

        List<TrainingSession> result = new ArrayList<>();
        for (TimeOfDay t : map.navigableKeySet()) {
            result.addAll(map.get(t));
        }
        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, List<TrainingSession>> map = timetable.get(dayOfWeek);
        if (map == null) return List.of();
        List<TrainingSession> list = map.get(timeOfDay);
        if (list == null) return List.of();
        return new ArrayList<>(list);
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counts = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> perDay : timetable.values()) {
            for (List<TrainingSession> sessions : perDay.values()) {
                for (TrainingSession s : sessions) {
                    Coach c = s.getCoach();
                    counts.put(c, counts.getOrDefault(c, 0) + 1);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : counts.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        result.sort((a, b) -> Integer.compare(b.getCount(), a.getCount()));
        return result;
    }
}

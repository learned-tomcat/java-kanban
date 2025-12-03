package gym;

public class TimeOfDay implements Comparable<TimeOfDay> {

    private int hours;
    private int minutes;

    public TimeOfDay(int hours, int minutes) {
        if (hours < 0 || hours > 23)
            throw new IllegalArgumentException("Hours must be 0..23");
        if (minutes < 0 || minutes > 59)
            throw new IllegalArgumentException("Minutes must be 0..59");
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(TimeOfDay o) {
        if (hours != o.hours) return hours - o.hours;
        return minutes - o.minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeOfDay)) return false;
        TimeOfDay t = (TimeOfDay) o;
        return hours == t.hours && minutes == t.minutes;
    }

    @Override
    public int hashCode() {
        return hours * 100 + minutes;
    }

    @Override
    public String toString() {
        return "%02d:%02d".formatted(hours, minutes);
    }
}
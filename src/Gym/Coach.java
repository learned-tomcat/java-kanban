package Gym;

import java.util.Objects;

public class Coach {

    private String surname;
    private String name;
    private String middleName;

    public Coach(String surname, String name, String middleName) {
        this.surname = surname;
        this.name = name;
        this.middleName = middleName;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coach)) return false;
        Coach c = (Coach) o;
        return surname.equals(c.surname)
                && name.equals(c.name)
                && middleName.equals(c.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, middleName);
    }
}

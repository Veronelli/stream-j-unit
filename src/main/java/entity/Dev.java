package entity;

import java.util.List;
import java.util.Objects;

public class Dev {

    private String name;
    private Integer age;

    static Long idGenerator = 0L;

    public Dev() {
        this(null, null);

    }

    public Dev(String name, Integer age) {
        this.name = name;
        this.age = age;

    }

    @Override
    public String toString() {
        return "Dev{" + "name=" + name + ", age=" + age + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        Dev dev;
        try {
            dev = (Dev) obj;

        } catch (ClassCastException e) {
            return false;

        }
        return dev.getName().equals(this.getName()) && Objects.equals(dev.getYear(), this.getYear());

    }
}

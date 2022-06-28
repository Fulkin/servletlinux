package ru.fulkin.servletlinux.additionalTasks.inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "instructor")
public class Instructor extends Person {
    private int expirience;

    public int getExpirience() {
        return expirience;
    }

    public void setExpirience(int expirience) {
        this.expirience = expirience;
    }

    @Override
    public String toString() {
        return super.toString() + "{ Instructor{" +
                "expirience=" + expirience +
                "} }";
    }
}

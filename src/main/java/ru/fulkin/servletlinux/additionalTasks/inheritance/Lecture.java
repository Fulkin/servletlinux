package ru.fulkin.servletlinux.additionalTasks.inheritance;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lecture")
public class Lecture extends Person {
    private String university;
    private int grade;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return super.toString() + "{ Lecture{" +
                "university='" + university + '\'' +
                ", grade=" + grade +
                "} }";
    }
}

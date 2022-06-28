package ru.fulkin.servletlinux.additionalTasks.inheritance;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student extends Person {
    @Column(name = "student_number")
    private int studentNumber;
    @Column(name = "group_st")
    private String group;

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return super.toString() + "{ Student{" +
                "studentNumber=" + studentNumber +
                ", group='" + group + '\'' +
                "} }";
    }
}

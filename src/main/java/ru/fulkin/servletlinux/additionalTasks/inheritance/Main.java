package ru.fulkin.servletlinux.additionalTasks.inheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.fulkin.servletlinux.util.HibernateUtil;

import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Student student = getStudent();
        Lecture lecture = getLecture();
        Instructor instructor = getInstructor();



        Session session;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction  = session.beginTransaction();
            session.persist(student);
            session.persist(lecture);
            session.persist(instructor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }

        Session session1;
        Transaction transaction1 = null;
        try {
            session1 = sessionFactory.openSession();
            transaction1  = session1.beginTransaction();
            List personP = session1.createQuery("select p from Person p").list();
            for (int i = 0; i < personP.size(); i++) {
                System.out.println(personP.get(i));
            }
            transaction1.commit();
        } catch (Exception e) {
            if (transaction1 != null) {
                transaction1.rollback();
            }
            throw e;
        }

    }

    private static Instructor getInstructor() {
        Instructor instructor = new Instructor();
        instructor.setExpirience(5);
        instructor.setFirstName("Eugene");
        instructor.setLastName("Turin");
        return instructor;
    }

    private static Lecture getLecture() {
        Lecture lecture = new Lecture();
        lecture.setGrade(468);
        lecture.setUniversity("university");
        lecture.setFirstName("Vasya");
        lecture.setLastName("Vasiliev");
        return lecture;
    }

    private static Student getStudent() {
        Student student = new Student();
        student.setStudentNumber(1456);
        student.setGroup("G233");
        student.setFirstName("Alex");
        student.setLastName("Shmidt");
        return student;
    }
}

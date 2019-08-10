package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo {

    public static void main(String[]args){


        //Create SessionFactory
        SessionFactory factory = new Configuration ()
                .configure ("hibernate.cfg.xml")
                .addAnnotatedClass (Instructor.class )
                .addAnnotatedClass (InstructorDetail.class )
                .addAnnotatedClass (Course.class )
                .addAnnotatedClass (Review.class )
                .addAnnotatedClass (Student.class )
                .buildSessionFactory ();

        //Create Session
        Session session = factory.getCurrentSession ();


        try{


            //start a transaction
            session.beginTransaction ();

            Course tempCourse = new Course ( "Pacman - How to score one  million points" );

            System.out.println ("\nSaving the course...");
            session.save ( tempCourse );
            System.out.println ("Saved the course: " +tempCourse);

            Student tempStudent1 = new Student ( "John","Doe","johndoe@luv2code.com" );
            Student tempStudent2 = new Student ( "Mary","public","mary@luv2code.com" );

            tempCourse.addStudent ( tempStudent1 );
            tempCourse.addStudent ( tempStudent2 );

            System.out.println ("Saving student");
            session.save ( tempStudent1 );
            session.save ( tempStudent2 );
            System.out.println ("Saved students "+ tempCourse.getStudents ());


            //commit transaction
            session.getTransaction ().commit ();
            System.out.println ("Done!!!");

        }
        finally {
            //add clean up code
            session.close ();
            factory.close ();
        }
    }
}

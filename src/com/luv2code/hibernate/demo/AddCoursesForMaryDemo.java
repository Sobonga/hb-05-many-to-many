package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo {

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

           int studentId=2;
           Student tempStudent = session.get ( Student.class,studentId );

            System.out.println ("Loaded Student "+ tempStudent);
            System.out.println ("Courses "+tempStudent.getCourses () );

            Course tempCourse1 = new Course ( "Rubik's Cube - How to speed cube" );
            Course tempCourse2 = new Course ( "Atari 2600 - Game Development" );

            tempCourse1.addStudent ( tempStudent );
            tempCourse2.addStudent ( tempStudent );

            session.save ( tempCourse1 );
            session.save ( tempCourse2 );
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

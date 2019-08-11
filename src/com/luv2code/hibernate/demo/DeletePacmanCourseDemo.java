package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeletePacmanCourseDemo {

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

            int courseId = 10;
            Course tempCourse = session.get ( Course.class,courseId );

            session.delete ( tempCourse );

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

package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviesDemo {

    public static void main(String[]args){


        //Create SessionFactory
        SessionFactory factory = new Configuration ()
                .configure ("hibernate.cfg.xml")
                .addAnnotatedClass (Instructor.class )
                .addAnnotatedClass (InstructorDetail.class )
                .addAnnotatedClass (Course.class )
                .addAnnotatedClass (Review.class )
                .buildSessionFactory ();

        //Create Session
        Session session = factory.getCurrentSession ();


        try{


            //start a transaction
            session.beginTransaction ();

            Course tempCourse = new Course ( "Pacman - How to score one  million points" );

            tempCourse.addReview ( new Review ( "Great course ..........love it" ) );
            tempCourse.addReview ( new Review ( "Great course, job well done" ) );
            tempCourse.addReview ( new Review ("What a dumb course, you are an idiot" ) );


            System.out.println ("Saving the course ");
            System.out.println (tempCourse);
            System.out.println (tempCourse.getReviews ());

            session.save ( tempCourse );
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

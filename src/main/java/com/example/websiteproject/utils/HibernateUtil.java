package com.example.websiteproject.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;

public class HibernateUtil implements Serializable {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                // Create the SessionFactory from standard (hibernate.cfg.xml) config file.
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex)
            {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

        return sessionFactory;
    }
}


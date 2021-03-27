package com.project.dao;

import com.project.entity.Church;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChurchDao {
    public List<Church> show() {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Church.class)
                .buildSessionFactory();

        List<Church> churchList;

        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();

            churchList = session.createQuery("from Church").getResultList();
        } finally {
            factory.close();
        }

        return  churchList;
    }

    public ChurchDao() {
    }

}

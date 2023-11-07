package com.grig.edu;

import com.grig.edu.entities.Employee;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        //configuration.addAnnotatedClass(Employee.class);
        //configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
            var employee = Employee.builder()
                    .username("alexgrig")
                    .firstName("Alex")
                    .lastName("Grig")
                    .age(37)
                    .birthDate(LocalDate.of(1986, 7, 3))
                    .build();

            session.save(employee);
            session.getTransaction().commit();
        }
    }
}
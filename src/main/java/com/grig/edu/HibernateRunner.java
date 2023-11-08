package com.grig.edu;

import com.grig.edu.converters.BirthDateConverter;
import com.grig.edu.entities.Employee;
import com.grig.edu.entities.Role;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.cfg.Configuration;


public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        //configuration.addAnnotatedClass(Employee.class);
        //configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAttributeConverter(BirthDateConverter.class);
        configuration.registerTypeOverride(new JsonBinaryType());

        configuration.configure();

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();
//            var employee = Employee.builder()
//                    .username("alexgrig")
//                    .firstName("Alex")
//                    .lastName("Grig")
//                    .birthDate(new BirthDate(LocalDate.of(1986, 7, 3)))
//                    .build();
//
//            session.save(employee);

            var alexgrig = session.get(Employee.class, "alexgrig");
            alexgrig.setRole(Role.ADMIN);
            alexgrig.setInfo("""
                    {
                        "name": "Aleksandr",
                        "profession": "programmer",
                        "sport": "roller"
                    }
                    """.trim());

            session.flush();

            session.getTransaction().commit();
        }
    }
}
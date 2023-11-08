package com.grig.edu;

import com.grig.edu.converters.BirthDateConverter;
import com.grig.edu.entities.BirthDate;
import com.grig.edu.entities.Employee;
import com.grig.edu.entities.PersonalInfo;
import com.grig.edu.entities.Role;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;


@Slf4j
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
            var employee = Employee.builder()
                    .username("lohness2")
                    .personalInfo(PersonalInfo.builder()
                                    .firstName("Ivan")
                                    .lastName("Ivanov")
                                    .bDate(new BirthDate(LocalDate.of(1999, 2, 15)))
                                    .build())
                    .role(Role.USER)
                    .build();

            session.delete(employee);


//            var alexgrig = session.get(Employee.class, "alexgrig");
//            alexgrig.setRole(Role.ADMIN);
//            alexgrig.setInfo("""
//                    {
//                        "name": "Aleksandr",
//                        "profession": "programmer",
//                        "sport": "roller"
//                    }
//                    """.trim());
//
//            session.flush();

            log.info("-------Log about our session: {} -----------", session.getEntityName(employee));

            session.getTransaction().commit();
        }
    }
}
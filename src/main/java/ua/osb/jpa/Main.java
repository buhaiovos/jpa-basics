package ua.osb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Nothing is happening before here...");

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ua.osb.jpa-learning");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        log.info("Searching for a person with id 1");
        Person person = entityManager.find(Person.class, 1L);
        log.info("Searching for an office with id 1");
        Office office = entityManager.find(Office.class, 1L);

        log.info("Person is found: {}", person);
        log.info("Office is found: {}", office);
    }
}

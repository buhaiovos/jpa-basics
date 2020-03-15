package ua.osb.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Nothing is happening before here...");

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ua.osb.jpa-learning");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        log.info("Searching for a person with id 1");
        Person person = entityManager.find(Person.class, 1L);
        log.info("Person is found: {}", person);

        log.info("Searching for an office with id 1");
        Office office = entityManager.find(Office.class, 1L);
        log.info("Office is found: {}", office);


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        log.info("Searching for all persons using Criteria API...");
        CriteriaQuery<Person> personQuery = criteriaBuilder.createQuery(Person.class);
        personQuery.from(Person.class);
        TypedQuery<Person> typedPersonQuery = entityManager.createQuery(personQuery);
        List<Person> persons = typedPersonQuery.getResultList();
        log.info("Found: {}", persons); // omg, why there is sooo much boilerplate ?!?!?!

        log.info("Searching for all offices using Criteria API...");
        CriteriaQuery<Office> officeQuery = criteriaBuilder.createQuery(Office.class);
        officeQuery.from(Office.class);
        TypedQuery<Office> typedQuery = entityManager.createQuery(officeQuery);
        List<Office> offices = typedQuery.getResultList();
        log.info("Found: {}", offices);

        log.info("Adding Eddie...");
        var eddie = new Person();
        eddie.setName("Eddie Lebowski");
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(eddie);
        transaction.commit();
        log.info("Added.");

        log.info("Fetch all persons using JPQL to make sure...");
        TypedQuery<Person> allQuery = entityManager.createQuery("select p from Person p", Person.class);
        List<Person> allPersons = allQuery.getResultList();
        log.info("Got: {}", allPersons);

        log.info("Searching for all persons who are tied to an office using Criteria API...");
        CriteriaQuery<Person> personJoinQuery = criteriaBuilder.createQuery(Person.class);
        personJoinQuery.from(Person.class).join("office");
        TypedQuery<Person> personJoinTypedQuery = entityManager.createQuery(personJoinQuery);
        List<Person> personWithOffices = personJoinTypedQuery.getResultList();
        log.info("These have office: {}", personWithOffices);

        log.info("Find person with id 1 using Criteria API...");
        CriteriaQuery<Person> findByIdQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> personRoot = findByIdQuery.from(Person.class);
        findByIdQuery.select(personRoot).where(criteriaBuilder.equal(personRoot.get("id"), 1L));
        TypedQuery<Person> personByIdTypedQuery = entityManager.createQuery(findByIdQuery);
        Person idOne = personByIdTypedQuery.getSingleResult();
        log.info("It was a long ride, but this is what we've found: {}", idOne);
    }
}

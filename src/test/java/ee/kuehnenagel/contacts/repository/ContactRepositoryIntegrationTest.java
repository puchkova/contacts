package ee.kuehnenagel.contacts.repository;

import ee.kuehnenagel.contacts.entity.Contact;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsNot.not;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ContactRepositoryIntegrationTest {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ContactRepository repository;

    @Before
    public void before() {
        entityManager.persist(Contact.from("name1", "photo1"));
        entityManager.persist(Contact.from("name2", "photo2"));
        entityManager.persist(Contact.from("name3", "photo3"));
        entityManager.persist(Contact.from("name5", "photo5"));
        entityManager.persist(Contact.from("name4", "photo4"));


    }
    @Test
    public void findByNameContainsIgnoreCaseReturnsCorrectValuesWhenMatchingIgnoreCase() {
        Contact name1 = Contact.from("name1", "photo1");
        Contact name2 = Contact.from("name2", "photo2");
        Contact name3 = Contact.from("name3", "photo3");
        Contact name4 = Contact.from("name4", "photo4");
        Contact name5 = Contact.from("name5", "photo5");

        List<Contact> contacts = repository.findByNameContainsIgnoreCase("naMe3");

        assertThat(contacts, contains(name3));
        assertThat(contacts, not(contains(name1)));
        assertThat(contacts, not(contains(name2)));
        assertThat(contacts, not(contains(name4)));
        assertThat(contacts, not(contains(name5)));
    }
}

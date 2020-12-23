package ee.kuehnenagel.contacts.repository;

import ee.kuehnenagel.contacts.model.Contact;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactRepositoryImplTest {

    @Autowired
    private ContactRepositoryImpl repository;

    private final String file = "src/test/people-test.csv";
    private String name = "Tatjana";

    @Test
    public void getContactsFromCsvFileReturnsFilteredContacts() {
        ArrayList<Contact> contactList = repository.getContactsFromCsvFile(name, file);

        assertEquals(3, contactList.size());
    }
    @Test
    public void getContactsFromCsvFileReturnsAllContacts() {
        ArrayList<Contact> contactList = repository.getContactsFromCsvFile(null, file);

        assertEquals(9, contactList.size());
    }
}

package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.repository.ContactRepositoryImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {
    @Mock
    private ContactRepositoryImpl repository;
    @InjectMocks
    private ContactService service;

    private String name = "Simpson";
    private ArrayList<Contact> contacts = new ArrayList<>();
    private String file = "src/test/people-test.csv";

    @Test
    public void getContactsShouldCallGetContactsFromCsvFileFromContactRepositoryOnlyOneTime() {
        //given
        when(repository.getContactsFromCsvFile(name, file)).thenReturn(contacts);
        service.setFile(file);

        //when
        service.getContacts(name);

        //then
        verify(repository, times(1)).getContactsFromCsvFile(name, file);
        assertNotNull(repository.getContactsFromCsvFile(name, file));
    }

    @Test
    public void getContactsIsNotNull() {
        assertNotNull(service.getContacts(name));
    }

}

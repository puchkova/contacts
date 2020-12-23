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

    private String name = "simpson";

    @Test
    public void getContactsCallsGetContactsFromCsvFileFromContactRepositoryOnlyOneTime() {
        //given
        String file = "src/test/people-test.csv";
        when(repository.getContactsFromCsvFile(name, file)).thenReturn(new ArrayList<>());
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

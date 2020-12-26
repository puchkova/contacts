package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.model.Contact;

import ee.kuehnenagel.contacts.repository.ContactRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactServiceTest {

    @Mock
    private ContactRepository repositoryMock;
    @InjectMocks
    private ContactService serviceUnderTest;

    @Autowired
    private ContactService service;

    private String name = "simpson";
    private final String file = "src/test/people-test.csv";

    @Test
    public void getAllContactsCallsFindAllFromRepositoryOnlyOneTime() {
        //given
        when(repositoryMock.findAll()).thenReturn(new ArrayList<>());

        //when
        serviceUnderTest.getContacts(name);

        //then
        verify(repositoryMock, times(1)).findAll();
        assertNotNull(repositoryMock.findAll());
    }

    @Test
    public void getContactsIsNotNull() {
        assertNotNull(serviceUnderTest.getContacts(name));
    }

        @Test
    public void getContactsReturnsFilteredContacts() { //TODO: fix test
        service.saveContactFromFileToRepository(file);
        List<Contact> contacts = service.getContacts("tatjana");

        assertNotNull(contacts);
        assertEquals(3, contacts.size());

        assertThat(contacts.get(0).getName()).containsIgnoringCase("tatjana");
        assertThat(contacts.get(0).getName()).isEqualTo("Tatjana Putskova");
        assertThat(contacts.get(1).getName()).isEqualTo("Tatjana Larina");

        assertThat(contacts.get(2).getName()).isEqualTo("Tatjana Romanova");
        assertThat(contacts.get(1).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936");
        assertThat(contacts.get(2).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/6/65/Bart_Simpson.png/revision/latest/scale-to-width-down/87?cb=20180319061933");
    }

    @Test
    public void getContactsReturnsAllContacts() { //TODO: fix test
        service.saveContactFromFileToRepository(file);
        List<Contact> contacts = service.getContacts("    ");

        assertNotNull(contacts);
        assertEquals(11, contacts.size());

        assertThat(contacts.get(0).getName()).containsIgnoringCase("simpson");
        assertThat(contacts.get(0).getName()).isEqualTo("Homer Simpson");
        assertThat(contacts.get(1).getName()).isEqualTo("Tatjana Putskova");

        assertThat(contacts.get(10).getName()).isEqualTo("Krusty the Clown");
        assertThat(contacts.get(5).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/5/57/Lisa_Simpson2.png/revision/latest/scale-to-width-down/74?cb=20180319000458");
    }

    @Test
    public void getContactsReturnsCorrectContactInCaseOfExtraCommaInTheLine() { //TODO: fix test
        service.saveContactFromFileToRepository(file);
        List<Contact> contacts = service.getContacts("Jr");

        assertThat(contacts.get(0).getName()).containsIgnoringCase("Jr");
        assertThat(contacts.get(0).getName()).isEqualTo("Bart Simpson, Jr.");
        assertThat(contacts.get(0).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/c/c0/Bart_simpsons_jr.png/revision/latest/scale-to-width-down/74?cb=20111109022228");
    }

}

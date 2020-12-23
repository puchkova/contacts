package ee.kuehnenagel.contacts.repository;

import ee.kuehnenagel.contacts.model.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ContactRepositoryImplTest {

    @Autowired
    private ContactRepositoryImpl repository;

    private final String file = "src/test/people-test.csv";

    @Test
    public void getContactsFromCsvFileReturnsFilteredContacts() {
        String name = "tatjana";
        ArrayList<Contact> contacts = repository.getContactsFromCsvFile(name, file);

        assertNotNull(contacts);
        assertEquals(3, contacts.size());

        assertThat(contacts.get(0).getName()).containsIgnoringCase(name);
        assertThat(contacts.get(0).getName()).isEqualTo("Tatjana Putskova");
        assertThat(contacts.get(1).getName()).isEqualTo("Tatjana Larina");

        assertThat(contacts.get(2).getName()).isEqualTo("Tatjana Romanova");
        assertThat(contacts.get(1).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936");
        assertThat(contacts.get(2).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/6/65/Bart_Simpson.png/revision/latest/scale-to-width-down/87?cb=20180319061933");
    }

    @Test
    public void getContactsFromCsvFileReturnsAllContacts() {
        ArrayList<Contact> contacts = repository.getContactsFromCsvFile("    ", file);

        assertNotNull(contacts);
        assertEquals(11, contacts.size());

        assertThat(contacts.get(0).getName()).containsIgnoringCase("simpson");
        assertThat(contacts.get(0).getName()).isEqualTo("Homer Simpson");
        assertThat(contacts.get(1).getName()).isEqualTo("Tatjana Putskova");

        assertThat(contacts.get(10).getName()).isEqualTo("Krusty the Clown");
        assertThat(contacts.get(5).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/5/57/Lisa_Simpson2.png/revision/latest/scale-to-width-down/74?cb=20180319000458");
    }

    @Test
    public void getContactsFromCsvFileReturnsCorrectContactInCaseOfExtraCommaInTheLine() {
        String name = "Jr";
        ArrayList<Contact> contacts = repository.getContactsFromCsvFile(name, file);

        assertThat(contacts.get(0).getName()).containsIgnoringCase(name);
        assertThat(contacts.get(0).getName()).isEqualTo("Bart Simpson, Jr.");
        assertThat(contacts.get(0).getUrl()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/c/c0/Bart_simpsons_jr.png/revision/latest/scale-to-width-down/74?cb=20111109022228");
    }
}

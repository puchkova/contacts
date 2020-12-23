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
    private String name = "tatjana";

    @Test
    public void getContactsFromCsvFileReturnsFilteredContacts() {
        ArrayList<Contact> contacts = repository.getContactsFromCsvFile(name, file);

        assertNotNull(contacts);
        assertEquals(3, contacts.size());

        assertThat(contacts.get(0).getName()).containsIgnoringCase(name);
        assertThat(contacts.get(0).getName()).isEqualTo("Tatjana Putskova");
        assertThat(contacts.get(1).getName()).isEqualTo("Tatjana Larina");
        assertThat(contacts.get(2).getName()).isEqualTo("Tatjana Romanova");
        assertThat(contacts.get(1).getUrl().trim()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/4/4d/MargeSimpson.png/revision/latest/scale-to-width-down/78?cb=20180314071936");
    }

    @Test
    public void getContactsFromCsvFileReturnsAllContacts() {
        ArrayList<Contact> contacts = repository.getContactsFromCsvFile(null, file);

        assertNotNull(contacts);
        assertEquals(9, contacts.size());

        assertThat(contacts.get(3).getName()).containsIgnoringCase("simpson");
        assertThat(contacts.get(0).getName()).isEqualTo("Tatjana Putskova");
        assertThat(contacts.get(4).getName()).isEqualTo("Maggie Simpson");
        assertThat(contacts.get(8).getName()).isEqualTo("Krusty the Clown");
        assertThat(contacts.get(3).getUrl().trim()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/5/57/Lisa_Simpson2.png/revision/latest/scale-to-width-down/74?cb=20180319000458");
        assertThat(contacts.get(7).getUrl().trim()).isEqualTo("https://vignette.wikia.nocookie.net/simpsons/images/a/a7/Montgomery_Burns.png/revision/latest/scale-to-width-down/121?cb=20100602062705");
    }
}

package ee.kuehnenagel.contacts.service;

import com.opencsv.CSVReader;
import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.repository.ContactRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService {

    @Getter
    @Setter
    @Value("src/main/resources/people.csv")
    private String file;

    private final ContactRepository repository;

    public List<Contact> getContacts(String name) {
        if (name != null && !name.trim().isEmpty() && name.length() != 0) {
            return getContactsByName(name);
        } else {
            return getAllContacts();
        }
    }

    public void saveContactFromFileToRepository(String file) {
        Contact newContact;
        try {
            FileInputStream fis = new FileInputStream(new File(file));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] row;
            reader.readNext();

            while ((row = reader.readNext()) != null) {
                newContact = createNewContact(row);
                repository.save(newContact);
            }

            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Contact> getAllContacts() {
        return new ArrayList<>(repository.findAll());
    }

    private List<Contact> getContactsByName(String name) {
        List<Contact> allContacts = getAllContacts();
        List<Contact> filteredContacts = new ArrayList<>();

        for (Contact contact : allContacts) {
            if (contact.getName().toLowerCase().contains(name.toLowerCase())) {
                filteredContacts.add(contact);
            }
        }
        return filteredContacts;
    }

    private Contact createNewContact(String[] row) {
        String name;
        String url;
        if (row[2].isEmpty()) {
            name = row[0];
            url = row[1].trim();
        } else {
            name = row[0] + "," + row[1];
            url = row[2].trim();
        }
        return new Contact(name, url);
    }
}

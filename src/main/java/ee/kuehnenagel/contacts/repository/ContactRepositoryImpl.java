package ee.kuehnenagel.contacts.repository;

import com.opencsv.CSVReader;
import ee.kuehnenagel.contacts.model.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

@RequiredArgsConstructor
@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public ArrayList<Contact> getContactsFromCsvFile(String name, String file) {
        ArrayList<Contact> contactList = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(new File(file));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] row;
            reader.readNext();

            while ((row = reader.readNext()) != null) {

                Contact newContact = createNewContact(row);
                filterContacts(name, newContact, contactList);
            }

            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return contactList;
    }

    private Contact createNewContact(String[] row) {
        String name;
        String url;
        if (row[2].isEmpty()) {
            name = row[0];
            url = row[1];
        } else {
            name = row[0] + row[1];
            url = row[2];
        }
        return new Contact(name, url);
    }

    private void filterContacts(String name, Contact newContact, ArrayList<Contact> contacts) {
        if (name != null && !name.trim().isEmpty() && name.length() != 0) {
            if (newContact.getName().toLowerCase().contains(name.toLowerCase())) {
                contacts.add(newContact);
            }
        } else {
            contacts.add(newContact);
        }
    }
}

package ee.kuehnenagel.contacts.repository;

import ee.kuehnenagel.contacts.model.Contact;

import com.opencsv.CSVReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;

@RequiredArgsConstructor
@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public ArrayList<Contact> getContactsFromCsvFile(String name, String file) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(new File(file));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] nextLine;
            reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                Contact newContact = new Contact(nextLine[0],
                        nextLine[1]);

                filterContacts(name, newContact, contacts);
            }
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    private void filterContacts(String name, Contact newContact, ArrayList<Contact> contacts) {
        if (name != null && !name.trim().isEmpty() && name.length()!=0) {
            if (newContact.getName().toLowerCase().contains(name.toLowerCase())) {
                contacts.add(newContact);
            }
        } else {
            contacts.add(newContact);
        }
    }
}

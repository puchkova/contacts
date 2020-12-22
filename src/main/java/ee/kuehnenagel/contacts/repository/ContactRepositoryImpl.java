package ee.kuehnenagel.contacts.repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import org.springframework.stereotype.Repository;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.service.ContactService;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private final ArrayList contacts;

    public ContactRepositoryImpl() {
        contacts = new ArrayList();
    }

    @Override
    public ArrayList<Contact> findAll(String name) {
        FileInputStream fis = null;

        try {
            String fileName = "src/main/resources/people.csv";

            fis = new FileInputStream(new File(fileName));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] nextLine;
            reader.readNext();

            contacts.clear();
            while ((nextLine = reader.readNext()) != null) {

                Contact newContact = new Contact(nextLine[0],
                        nextLine[1]);

                if(name==null) {
                    contacts.add(newContact);
                }
                else
                    {
                    if (newContact.getName().toLowerCase().contains(name.toLowerCase())) {
                        contacts.add(newContact);
                    }
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ContactService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contacts;
    }
}

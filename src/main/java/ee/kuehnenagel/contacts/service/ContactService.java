package ee.kuehnenagel.contacts.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import ee.kuehnenagel.contacts.model.Contact;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class ContactService implements IContactService {

    private final ArrayList contacts;

    public ContactService() {

        contacts = new ArrayList();
    }

    @Override
    public ArrayList<Contact> findAll() {

        FileInputStream fis = null;

        try {

            String fileName = "src/main/resources/people.csv";

            fis = new FileInputStream(new File(fileName));
            CSVReader reader = new CSVReader(new InputStreamReader(fis));
            String[] nextLine;
            reader.readNext();

            while ((nextLine = reader.readNext()) != null) {

                Contact newContact = new Contact(nextLine[0],
                        nextLine[1]);
                contacts.add(newContact);
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

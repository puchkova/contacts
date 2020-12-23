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

    private final ArrayList<Contact> contacts;

    @Override
    public ArrayList<Contact> getContactsFromCsvFile(String name) {
       try {
           String fileName = "src/main/resources/people.csv";
           FileInputStream fis = new FileInputStream(new File(fileName));
           CSVReader reader = new CSVReader(new InputStreamReader(fis));
           String[] nextLine;
           reader.readNext();

           contacts.clear();
           while ((nextLine = reader.readNext()) != null) {

               Contact newContact = new Contact(nextLine[0],
                       nextLine[1]);

               if (name == null) {
                   contacts.add(newContact);
               } else {
                   if (newContact.getName().toLowerCase().contains(name.toLowerCase())) {
                       contacts.add(newContact);
                   }
               }
           }
           fis.close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
        return contacts;
    }
}

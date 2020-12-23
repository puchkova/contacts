package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.repository.ContactRepository;
import ee.kuehnenagel.contacts.repository.ContactRepositoryImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepositoryImpl repository;

    @Getter @Setter
    @Value("src/main/resources/people.csv")
    private String file;

    public ArrayList<Contact> getContacts(String name) {

        return repository.getContactsFromCsvFile(name, file);
    }
}

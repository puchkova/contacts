package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository repository;

    public ArrayList<Contact> findAll(String name) {
        return repository.findAll(name);
    }
}

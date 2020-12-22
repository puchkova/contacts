package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.repository.ContactRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public ArrayList<Contact> findAll(String name) {
        return repository.findAll(name);
    }
}

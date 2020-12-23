package ee.kuehnenagel.contacts.repository;

import ee.kuehnenagel.contacts.model.Contact;

import java.util.ArrayList;

public interface ContactRepository {

    ArrayList<Contact> getContactsFromCsvFile(String name, String file);
}

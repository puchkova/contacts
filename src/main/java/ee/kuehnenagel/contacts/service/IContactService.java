package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.model.Contact;

import java.util.ArrayList;

public interface IContactService {

    public ArrayList<Contact> findAll();
}

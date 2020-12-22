package ee.kuehnenagel.contacts.controller;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.service.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    public List<Contact> listContacts() {
        return contactService.findAll();
    }
}

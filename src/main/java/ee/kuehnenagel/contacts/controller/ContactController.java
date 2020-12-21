package ee.kuehnenagel.contacts.controller;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.service.IContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "localhost:3000", maxAge = 3600)
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    @Autowired
    private IContactService contactService;

    //@RequestMapping("/contacts")
    @GetMapping("/contacts")
    public List<Contact> listContacts() {

        return contactService.findAll();
    }
}

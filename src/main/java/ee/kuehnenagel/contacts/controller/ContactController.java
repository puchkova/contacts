package ee.kuehnenagel.contacts.controller;

import ee.kuehnenagel.contacts.entity.Contact;
import ee.kuehnenagel.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    private final ContactService service;

    @GetMapping("/contacts")
    @ResponseBody
    public List<Contact> getContacts(@RequestParam(required = false) String name) {
        return service.getContacts(name);
    }
}

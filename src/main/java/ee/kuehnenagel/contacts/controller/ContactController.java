package ee.kuehnenagel.contacts.controller;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contacts")
    @ResponseBody
    public List<Contact> listContacts(@RequestParam(required = false) String name) {
        return contactService.findAll(name);
    }

}

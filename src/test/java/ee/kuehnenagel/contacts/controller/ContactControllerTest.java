package ee.kuehnenagel.contacts.controller;

import ee.kuehnenagel.contacts.model.Contact;
import ee.kuehnenagel.contacts.service.ContactService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ContactControllerTest {
    @Mock
    private ContactService service;
    @InjectMocks
    private ContactController controller;
    @Autowired
    private MockMvc mockMvc;

    private String name = "Simpson";
    private ArrayList<Contact> contacts = new ArrayList<>();

    @Test
    public void getContactsResponseIsSuccessful() throws Exception {
        mockMvc.perform(get("/contacts")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getContactsShouldCallGetContactsFromContactServiceOnlyOneTime() {
        //given
        when(service.getContacts(name)).thenReturn(contacts);

        //when
        controller.getContacts(name);

        //then
        verify(service, times(1)).getContacts(name);
        assertNotNull(service.getContacts(name));
    }

    @Test
    public void getContactsIsNotNull() {
        assertNotNull(controller.getContacts(name));
    }
}

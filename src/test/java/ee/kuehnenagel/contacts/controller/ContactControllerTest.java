package ee.kuehnenagel.contacts.controller;

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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ContactControllerTest {
    @Mock
    private ContactService serviceMock;
    @InjectMocks
    private ContactController controllerUnderTest;
    @Autowired
    private MockMvc mockMvc;

    private String name = "simpson";

    @Test
    public void getContactsResponseIsSuccessful() throws Exception {
        mockMvc.perform(get("/contacts")).andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getContactsCallsGetContactsFromContactServiceOnlyOneTime() {
        //given
        when(serviceMock.getContacts(name)).thenReturn(new ArrayList<>());

        //when
        controllerUnderTest.getContacts(name);

        //then
        verify(serviceMock, times(1)).getContacts(name);
        assertNotNull(serviceMock.getContacts(name));
    }

    @Test
    public void getContactsIsNotNull() {
        assertNotNull(controllerUnderTest.getContacts(name));
    }
}

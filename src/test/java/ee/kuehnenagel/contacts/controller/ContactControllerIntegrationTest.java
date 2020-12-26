package ee.kuehnenagel.contacts.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;

import static org.junit.Assert.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ContactControllerIntegrationTest {
    @Autowired
    private ContactController controller;

    @Test
    public void getContactsIsNotNull() {
        assertNotNull(controller.getContacts(anyString()));
    }
}

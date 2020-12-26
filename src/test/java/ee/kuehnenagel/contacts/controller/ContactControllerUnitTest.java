package ee.kuehnenagel.contacts.controller;

import ee.kuehnenagel.contacts.service.ContactService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ContactControllerUnitTest {
    @Mock
    private ContactService serviceMock;
    @InjectMocks
    private ContactController controllerUnderTest;

    @Test
    public void getContactsCallsGetContactsFromContactServiceOnlyOneTime() {
        //given
        when(serviceMock.getContacts(anyString())).thenReturn(new ArrayList<>());

        //when
        controllerUnderTest.getContacts(anyString());

        //then
        verify(serviceMock, times(1)).getContacts(anyString());
        assertNotNull(serviceMock.getContacts(anyString()));
    }
}

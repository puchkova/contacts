package ee.kuehnenagel.contacts.service;

import ee.kuehnenagel.contacts.repository.ContactRepository;
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
public class ContactServiceUnitTest {

    @Mock
    private ContactRepository repositoryMock;
    @InjectMocks
    private ContactService serviceUnderTest;

    @Test
    public void getAllContactsCallsFindAllFromRepositoryOnlyOneTime() { //TODO: fix test
        //given
        when(repositoryMock.findAll()).thenReturn(new ArrayList<>());

        //when
        serviceUnderTest.getContacts("");

        //then
        verify(repositoryMock, times(1)).findAll();
        assertNotNull(repositoryMock.findAll());
    }
}

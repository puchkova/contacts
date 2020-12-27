package ee.kuehnenagel.contacts;

import ee.kuehnenagel.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final ContactService service;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        service.saveContactFromFileToRepository();
    }
}

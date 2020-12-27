package ee.kuehnenagel.contacts.repository;

import ee.kuehnenagel.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByNameContainsIgnoreCase(String name);
}

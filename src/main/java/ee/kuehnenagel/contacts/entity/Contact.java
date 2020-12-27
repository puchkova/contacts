package ee.kuehnenagel.contacts.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Contact {

    @Id
    @GeneratedValue
    @Column
    private long id;

    @Column
    private String name;

    @Column
    private String url;

    public Contact(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public static Contact from(String name, String url) {
        return new Contact(name, url);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name);
    }
}

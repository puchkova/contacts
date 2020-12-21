package ee.kuehnenagel.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Contact {
    private String name;
    private String url;
}

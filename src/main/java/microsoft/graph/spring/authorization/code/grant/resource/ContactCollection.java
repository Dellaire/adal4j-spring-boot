package microsoft.graph.spring.authorization.code.grant.resource;

import java.util.List;

/**
 * The {@code ContactCollection} maps JSON-data containing a collection of
 * contacts to a Java-object and provides its payload.
 */
public class ContactCollection {
    private List<Contact> value;

    public List<Contact> getValue() {
        return this.value;
    }

    public void setValue(final List<Contact> value) {
        this.value = value;
    }
}

package microsoft.graph.spring.authorization.code.grant.resource;

/**
 * The {@code Contact} maps JSON-data containing a contact to a Java-object and
 * provides its payload.
 */
public class Contact {
    private String surname;
    private String givenName;

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getGivenName() {
        return this.givenName;
    }

    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }
}
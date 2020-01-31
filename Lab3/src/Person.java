public class Person {
    private String firstName;
    private String lastName;

    // constructor
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    // get the first name of this person
    public String getFirstName() {
        return this.firstName;
    }

    // get the last name of this person
    public String getLastName() {
        return this.lastName;
    }
}

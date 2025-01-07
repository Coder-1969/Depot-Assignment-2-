package Model;

public class Customer {
    private String firstName;
    private String lastName;
    private String parcelID;

    // Constructor
    public Customer(String firstName, String lastName, String parcelID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parcelID = parcelID;
    }

    // Getter for full name
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    // Getter for parcel ID
    public String getParcelID() {
        return parcelID;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + parcelID + ")";
    }
}

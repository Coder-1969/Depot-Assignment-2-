public class Customer {
    private String firstName;
    private String lastName;
    private String parcelID;

    public Customer(String firstName, String lastName, String parcelID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.parcelID = parcelID;
    }

    public String getFullName() {
        return firstName + " " + lastName; // Combine with a space
    }

    public String getParcelID() {
        return parcelID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    @Override
    public String toString() {
        return "Customer [FullName= " + getFullName() + ", ParcelID= " + parcelID + "]";
    }
}

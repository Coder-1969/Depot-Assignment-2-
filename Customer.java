public class Customer {

    private String name;
    private String parcelID;

    public Customer(String name, String parcelID) {
        this.name = name;
        this.parcelID = parcelID;
    }

    public String getName() {
        return name;
    }

    public String getParcelID() {
        return parcelID;
    }
}

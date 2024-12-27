public class Customer {

    private String fname;
    private String lname;
    private String parcelID;

    public Customer(String fname, String lname, String parcelID) {
        this.fname = fname;
        this.lname = lname;
        this.parcelID = parcelID;
    }

    public String getfname() {
        return fname;
    }

    public String getlname() {
        return lname;
    }

    public String getParcelID() {
        return parcelID;
    }
}

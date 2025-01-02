public class DepotStaff {
    private final String name;
    private final int staffID;

    // Constructor
    public DepotStaff(String name, int staffID) {
        this.name = name;
        this.staffID = staffID;
    }

    // Getter for staff name
    public String getName() {
        return name;
    }

    // Getter for staff ID
    public int getStaffID() {
        return staffID;
    }

    // Method to process a customer and their parcel
    public void processCustomer(String customerName, Parcel parcel, String action) {
        switch (action.toLowerCase()) {
            case "collect":
                // Log the collection of the parcel by the customer
                System.out.println(customerName + " collected the parcel with ID: " + parcel.getParcelID());
                break;

            case "release":
                // Calculate the fee and log the release of the parcel by the staff
                double fee = calculateFee(parcel);
                System.out.println("Staff " + name + " (ID: " + staffID + ") released the parcel with ID: " + parcel.getParcelID());
                System.out.println("The fee for this parcel is: £" + fee);
                break;

            default:
                System.out.println("Unknown action: " + action);
                break;
        }
    }

    // Method to calculate the fee based on weight and days in depot
    public double calculateFee(Parcel parcel) {
        double weight = parcel.getWeight();
        int daysInDepot = parcel.getDaysInDepot();

        // A simple fee calculation: fee = (weight * 0.5) + (daysInDepot * 0.2)
        return (weight * 0.5) + (daysInDepot * 0.2);
    }
}

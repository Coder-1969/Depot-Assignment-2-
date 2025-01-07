package Controller;

import Model.Parcel;

public class DepotStaff {
    private final String name;
    private final int staffID;

    public DepotStaff(String name, int staffID) {
        this.name = name;
        this.staffID = staffID;
    }

    public String getName() {
        return name;
    }

    public int getStaffID() {
        return staffID;
    }

    public void processCustomer(String customerName, Parcel parcel, String action) {
        switch (action.toLowerCase()) {
            case "collect":
                System.out.println(customerName + " collected the parcel with ID: " + parcel.getParcelID());
                break;
            case "release":
                double fee = calculateFee(parcel);
                System.out.println("Staff " + name + " (ID: " + staffID + ") released the parcel with ID: " + parcel.getParcelID());
                System.out.println("The fee for this parcel is: £" + fee);
                break;
            default:
                System.out.println("Unknown action: " + action);
                break;
        }
    }

    public double calculateFee(Parcel parcel) {
        double weight = parcel.getWeight();
        int daysInDepot = parcel.getDaysInDepot();

        double fee = (weight * 0.5) + (daysInDepot * 0.2);
        if (weight < 4) {
            double discount = fee * 0.1;
            fee -= discount;
            System.out.println("Discount applied: " + discount + " for parcel ID: " + parcel.getParcelID());
        }
        return fee;
    }
}

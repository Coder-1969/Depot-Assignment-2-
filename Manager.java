package Controller;

import Model.Customer;
import Model.Log;
import Model.Parcel;
import Model.ParcelMap;
import Model.Queue;
import View.ManagerView;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class Manager {
    private final Queue customerQueue;
    private final ParcelMap parcelMap;
    private final Log log;
    private final ManagerView view;  // View reference to update UI

    public Manager(ManagerView view) {
        this.view = view;  // Pass view to the Manager class
        customerQueue = new Queue();
        parcelMap = new ParcelMap();
        log = Log.getInstance();
    }

    // Main run method
    public void run() {
        loadData();
        displayData();
        processCustomer();
        saveData();
        writeLog();
    }

    // Load customer and parcel data
    public void loadData() {
        customerQueue.loadCustomers("C:/Users/HP/OneDrive - University of Hertfordshire/3rd Year/Software Arc/Assignment part-2/Depot_Assign-2/src/Files/Custs.csv");
        parcelMap.loadParcels("C:/Users/HP/OneDrive - University of Hertfordshire/3rd Year/Software Arc/Assignment part-2/Depot_Assign-2/src/Files/Parcels.csv");
        view.updateStatus("Data loaded successfully.");
    }

    // Display customer and parcel data
    public void displayData() {
        List<Customer> customers = customerQueue.getCustomers();
        StringBuilder customerData = new StringBuilder("Customers:\n");
        for (Customer customer : customers) {
            customerData.append(customer).append("\n");
        }

        Collection<Parcel> parcels = parcelMap.getAllParcels();
        StringBuilder parcelData = new StringBuilder("Parcels:\n");
        for (Parcel parcel : parcels) {
            parcelData.append(parcel).append("\n");
        }

        view.appendOutput(customerData.toString());
        view.appendOutput(parcelData.toString());
    }

    // Process customer (collect parcel)
    // Process customer (collect parcel)
    public void processCustomer() {
        Customer customer = customerQueue.removeCustomer(); // Correctly remove the first customer from the queue
        if (customer != null) {
            Parcel parcel = parcelMap.getParcel(customer.getParcelID());
            if (parcel != null) {
                DepotStaff staff = new DepotStaff("John Doe", 123);
                staff.processCustomer(customer.getFullName(), parcel, "collect");
                parcelMap.updateParcel(parcel.getParcelID(), parcel.getDaysInDepot() - 1, "collected");
                view.appendOutput("Processed customer: " + customer.getFullName() + " for parcel ID: " + parcel.getParcelID());
            } else {
                view.appendOutput("No parcel found for customer: " + customer.getFullName());
            }
        } else {
            view.appendOutput("No customers in the queue to process.");
        }
    }


    // Add a new customer
    public void addCustomer(Customer customer) {
        customerQueue.addCustomer(customer);
        view.appendOutput("Customer added: " + customer);
    }

    // Remove a customer from the queue after parcel collection
    public void removeCustomer(Customer customer) {
        customerQueue.removeCustomer();
        view.appendOutput("Customer removed: " + customer);
    }

    // Add new parcel to the list
    public void addParcel(Parcel parcel) {
        parcelMap.addParcel(parcel);
        view.appendOutput("Parcel added: " + parcel);
    }

    // Find a parcel by ID
    public Parcel findParcelById(String parcelId) {
        return parcelMap.getParcel(parcelId);
    }

    // Save customer and parcel data
    public void saveData() {
        customerQueue.saveCustomer(new File("UpdatedCustomers.csv"));
        parcelMap.saveParcels("UpdatedParcels.csv");
        view.updateStatus("Data saved successfully.");
    }

    public boolean removeCustomerByParcelID(String parcelID) {
        List<Customer> customers = customerQueue.getCustomers();

        // Find the customer with the matching Parcel ID
        for (Customer customer : customers) {
            if (customer.getParcelID().equalsIgnoreCase(parcelID)) {

                // Remove the customer from the queue
                customerQueue.removeCustomer();
                return true; // Customer found and removed
            }
        }
        return false; // No matching customer found
    }

    // Write log to file
    public void writeLog() {
        log.addEvent("Parcel collection and customer processing completed.");
        log.writeLogToFile("LogFile.txt");
        view.appendOutput("Log has been written to LogFile.txt.");
    }

    public void updateStatus(String s) {
        view.updateStatus(s);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Customer Queue:\n");
        for (Customer customer : customerQueue.getCustomers()) {
            sb.append(customer).append("\n");
        }

        sb.append("\nParcels:\n");
        for (Parcel parcel : parcelMap.getAllParcels()) {
            sb.append(parcel).append("\n");
        }

        return sb.toString();
    }

}

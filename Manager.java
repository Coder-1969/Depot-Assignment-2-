import java.io.File;
import java.io.Serializable;


public class Manager implements Serializable {

    private Queue customerQueue;  // Queue of customers awaiting parcel collection
    private ParcelMap parcelMap;  // Map managing parcels and their statuses
    private Log log;  // Singleton log instance for tracking events
    private GUI gui;  // GUI instance for later display


     // Constructor to initialize the Manager, customer queue, parcel map, and log.

    public Manager() {
        customerQueue = new Queue();  // Initialize Queue of Customers
        parcelMap = new ParcelMap();   // Initialize Parcel Map for managing parcels
        log = Log.getInstance();       // Get the singleton instance of the Log
        gui = null;                    // GUI is initialized as null for console-based I/O first
    }

    /*
     * Main method serves as the entry point for the program.
     * This method initializes the Manager and starts the operations,
     * and later it will be expanded to incorporate GUI.
     *
     * @param args Command line arguments (not used)
     */

    /**
     * The main workflow method that manages the loading of data,
     * processing of customers, updating parcels, and eventually
     * transitioning to a GUI-based interface.
     */
    public void run() {
        loadData();    // Load customer and parcel data
        displayData(); // Display data initially in the console
        processCustomer(); // Process a customer from the queue
        saveData();    // Save updated customer and parcel data
        writeLog();    // Write all events to a log file
        transitionToGUI(); // Eventually, transition to a GUI interface
    }

    /**
     * Loads customer and parcel data from external files.
     * This includes reading in customer details and parcel information.
     *
     * For console-based application, we will read from text or CSV files.
     */
    private void loadData() {
        System.out.println("Loading customers and parcels...");
        log.addEvent("Loading customers and parcels...");

        customerQueue.loadCustomers(); // Load customer data into the queue
        parcelMap.loadParcels("Parcels.csv"); // Load parcel data from a CSV file
    }

    /**
     * Displays loaded data (customers and parcels) in the console.
     * This is the first step before we transition to the GUI.
     */
    private void displayData() {
        System.out.println("Loaded customers:");
        customerQueue.displayCustomers(); // Display all customers in the queue
        log.addEvent("Displayed loaded customers.");

        System.out.println("Loaded parcels:");
        parcelMap.readParcels(); // Display all parcels in the map
        log.addEvent("Displayed loaded parcels.");
    }

    /**
     * Processes a customer from the queue, checks if their parcel is available,
     * and updates the parcel status upon collection.
     */
    private void processCustomer() {
        System.out.println("Processing a customer...");
        log.addEvent("Processing a customer...");

        Customer nextCustomer = customerQueue.removeCustomer(); // Remove the next customer from the queue
        if (nextCustomer != null) {
            System.out.println("Processing customer: " + nextCustomer);
            log.addEvent("Processing customer: " + nextCustomer);

            // Find the parcel associated with the customer
            Parcel parcel = parcelMap.getParcel(nextCustomer.getParcelID());
            if (parcel != null) {
                // Process the customer if their parcel is found
                DepotStaff depotStaff = new DepotStaff("John Doe", 123);
                depotStaff.processCustomer(nextCustomer.getFullName(), parcel, "release");

                // Update parcel status
                parcelMap.updateParcel(parcel.getParcelID(), parcel.getDaysInDepot(), "Collected");
                log.addEvent("Parcel " + parcel.getParcelID() + " collected by customer: " + nextCustomer.getFullName());
            } else {
                System.out.println("Parcel not found for ID: " + nextCustomer.getParcelID());
                log.addEvent("Parcel not found for ID: " + nextCustomer.getParcelID());
            }
        }
    }

    /**
     * Saves the updated customer and parcel data to external files.
     * This includes saving the new status of the parcels and updating the customer list.
     */
    private void saveData() {
        System.out.println("Saving updated parcels...");
        log.addEvent("Saving updated parcels...");
        parcelMap.saveParcels("UpdatedParcels.csv"); // Save updated parcels to CSV

        System.out.println("Saving updated customer queue...");
        log.addEvent("Saving updated customer queue...");
        customerQueue.saveCustomer(new File("UpdatedCustomers.csv")); // Save updated customers to file
    }

    /**
     * Writes all logged events to a log file.
     * This keeps a record of the operations performed during the session.
     */
    private void writeLog() {
        log.writeLogToFile("LogFile.txt"); // Save log entries to a text file

        System.out.println("All operations completed.");
        log.addEvent("All operations completed.");
    }

    /**
     * Placeholder method for transitioning to a GUI-based interface.
     * In the future, this method will initialize the GUI, displaying customer and parcel data in the GUI.
     * For now, it can simply initialize the GUI or display a message about transitioning.
     */
    private void transitionToGUI() {
        System.out.println("Transitioning to GUI (not yet implemented).");
        // Future development will create a GUI here, such as initializing a JFrame and displaying data.
        // gui = new GUI();
        // gui.displayData(customerQueue, parcelMap);
    }
}

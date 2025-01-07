package View;

import Controller.Manager;
import Model.Customer;
import Model.Parcel;
import View.ManagerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerController {
    private ManagerView view;
    private Manager manager;

    public ManagerController(ManagerView view) {
        this.view = view;
        this.manager = new Manager(view);

        initializeListeners();
    }

    private void initializeListeners() {
        // Load Data Button Listener
        view.addLoadButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.loadData();
                manager.displayData();
            }
        });

        // Display Data Button Listener
        view.addDisplayButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.displayData();
            }
        });

        // Process Customer Button Listener
        view.addProcessButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manager.processCustomer();
            }
        });

        // Add Customer Button Listener
        view.addAddCustomerButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer customer = view.getCustomerInput(); // Get customer input from view
                if (customer != null) {
                    manager.addCustomer(customer);  // Add customer to both queue and LinkedList
                }
            }
        });

        // Add Parcel Button Listener
        view.addAddParcelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Parcel parcel = (Parcel) view.getParcelInput();
                if (parcel != null) {
                    manager.addParcel(parcel);
                    view.appendOutput("Parcel added: " + parcel);
                }
            }
        });

        // Find Parcel Button Listener
        view.addFindParcelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String parcelID = view.getParcelIdInput(); // Get the parcel ID input from view
                if (parcelID != null && !parcelID.trim().isEmpty()) {
                    Parcel parcel = manager.findParcelById(parcelID.trim()); // Get the parcel using the ID
                    if (parcel != null) {
                        view.appendOutput("Parcel found: " + parcel);
                    } else {
                        view.appendOutput("Parcel not found for ID: " + parcelID);
                    }
                } else {
                    view.appendOutput("Invalid input. Please enter a valid Parcel ID.");
                }
            }
        });



        // Generate Report Button Listener
        view.addGenerateReportButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the manager's method to generate the report
                manager.generateReport();
            }
        });


    }
}

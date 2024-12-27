import java.io.*;
import java.util.LinkedList;

public class Queue {

    private LinkedList<Customer> customerLinkedList; // Replaced ArrayList with LinkedList

    public Queue() {
        customerLinkedList = new LinkedList<>();
    }

    // Adds user to our collection
    public void addCustomer(Customer customer) {
        customerLinkedList.add(customer);
    }

    // Saves users to the database file
    public void saveCustomer(File file) {
        try {
            // User model
            Customer customer;
            String save_data = "";

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            int i = 0;
            while (i < customerLinkedList.size()) {
                customer = customerLinkedList.get(i);
                save_data = customer.getFullName();
                bufferedWriter.write(save_data);
                bufferedWriter.newLine();
                i++;
            }
            // Prevents memory leak
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reads customers from the database file
    public void loadCustomers() {
        File file = new File("Custs.csv");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Assuming CSV format: fullName, parcelID
                String[] data = line.split(",");
                if (data.length == 2) {
                    String fullName = data[0].trim();
                    String[] nameParts = fullName.split(" ", 2);
                    String firstName = nameParts[0].trim();
                    String lastName = nameParts.length > 1 ? nameParts[1].trim() : ""; // Handle cases with no last name
                    String parcelID = data[1].trim();
                    Customer customer = new Customer(firstName, lastName, parcelID);
                    customerLinkedList.add(customer);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public void displayCustomers() {
        for (Customer customer : customerLinkedList) {
            System.out.println(customer);
        }
    }



 }

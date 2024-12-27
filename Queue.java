import java.io.*;
import java.util.ArrayList;

public class Queue {

    private ArrayList<Customer> customerArrayList;

    public Queue() {
        customerArrayList = new ArrayList<>();
    }

    // adds user to our collection
    public void addUser(Customer customer) {
        customerArrayList.add(customer);
    }

    // saves user to database file
    public void saveCustomer(File file) {
        try {
            // user model
            Customer customer;
            String save_data = "";

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            int i = 0;
            while( i < customerArrayList.size()) {
                customer = customerArrayList.get(i);
                save_data = customer.getfname() + ", " + customer.getlname();
                i++;
            }
            bufferedWriter.write(save_data);
            bufferedWriter.newLine();
            // prevents memory leak
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // reads user from database file
    public Object[] loadCustomers(File file) {
        Object[] objects;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            // each lines to array
            objects = bufferedReader.lines().toArray();
            bufferedReader.close();
            return objects;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class ParcelMap {
    private final TreeMap<String, Parcel> parcelTreeMap;

    // Constructor
    public ParcelMap() {
        parcelTreeMap = new TreeMap<>();
    }

    // Load parcels from a CSV file into the TreeMap
    public void loadParcels(String fileName) {
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Assuming CSV format: parcelID,length,width,height,weight,daysInDepot
                String[] data = line.split(",");
                if (data.length == 6) {  // Expecting 6 fields
                    String parcelID = data[0].trim();
                    double length = Double.parseDouble(data[1].trim());
                    double width = Double.parseDouble(data[2].trim());
                    double height = Double.parseDouble(data[3].trim());
                    double weight = Double.parseDouble(data[4].trim());
                    int daysInDepot = Integer.parseInt(data[5].trim());

                    // Create a Parcel object with the read data
                    Parcel parcel = new Parcel(parcelID, length, width, height, weight, daysInDepot, "In Depot");
                    parcelTreeMap.put(parcelID, parcel);
                } else {
                    System.out.println("Invalid line format: " + line);  // Debugging output
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    // Save parcels from the TreeMap to a file
    public void saveParcels(String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Parcel> entry : parcelTreeMap.entrySet()) {
                Parcel parcel = entry.getValue();
                String saveData = parcel.toString(); // Use the overridden toString method
                bufferedWriter.write(saveData);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read parcels from the TreeMap (prints to console for demonstration)
    public void readParcels() {
        for (Map.Entry<String, Parcel> entry : parcelTreeMap.entrySet()) {
            Parcel parcel = entry.getValue();
            System.out.println(parcel); // Use the overridden toString method for formatted output
        }
    }

    // Additional method to update parcel details
    public void updateParcel(String parcelID, int daysInDepot, String status) {
        Parcel parcel = parcelTreeMap.get(parcelID);
        if (parcel != null) {
            parcel.setDaysInDepot(daysInDepot);
            parcel.setStatus(status);
        } else {
            System.out.println("Parcel not found with ID: " + parcelID);
        }
    }

    // Additional method to retrieve a specific parcel
    public Parcel getParcel(String parcelID) {
        return parcelTreeMap.get(parcelID);
    }
}

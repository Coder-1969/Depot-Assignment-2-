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
                // Assuming CSV format: parcelID, dimension1, dimension2, dimension3, weight, daysInDepot
                String[] data = line.split(",");
                if (data.length == 6) {  // Now expecting 6 fields
                    String parcelID = data[0].trim();
                    String dimensions = data[1].trim() + "x" + data[2].trim() + "x" + data[3].trim(); // Combine dimensions
                    double weight = Double.parseDouble(data[4].trim()); // Weight
                    int daysInDepot = Integer.parseInt(data[5].trim()); // Days in depot
                    String status = "Unknown"; // Assuming "status" is not provided in this case

                    // Create a Parcel object with the read data
                    Parcel parcel = new Parcel(parcelID, dimensions, weight, daysInDepot, status);
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
                String saveData = parcel.getParcelID() + "," +
                        parcel.getDimensions() + "," +
                        parcel.getWeight() + "," +
                        parcel.getDaysInDepot() + "," +
                        parcel.getStatus();
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
            System.out.println(parcel);
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
}

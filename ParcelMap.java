package Model;

import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ParcelMap {
    private final TreeMap<String, Parcel> parcelTreeMap;

    public ParcelMap() {
        parcelTreeMap = new TreeMap<>();
    }

    // Loads parcels from a CSV file into the TreeMap
    public void loadParcels(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found: " + fileName);
            return;  // Exit if the file does not exist
        }

        parcelTreeMap.clear();  // Clear any previous data

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Print the raw line data for debugging
                System.out.println("Raw line: '" + line + "'");

                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                // Ensure that the data has exactly 6 columns
                if (data.length == 6) {
                    String parcelID = data[0].trim();
                    int daysInDepot = 0;
                    double length = 0.0, width = 0.0, height = 0.0, weight = 0.0;

                    try {
                        // Parse the fields from CSV
                        daysInDepot = Integer.parseInt(data[5].trim());  // Days in Depot is the 6th column (index 5)
                        length = Double.parseDouble(data[1].trim());     // Length is the 2nd column (index 1)
                        width = Double.parseDouble(data[2].trim());      // Width is the 3rd column (index 2)
                        height = Double.parseDouble(data[3].trim());     // Height is the 4th column (index 3)
                        weight = Double.parseDouble(data[4].trim());     // Weight is the 5th column (index 4)
                    } catch (NumberFormatException e) {
                        // Print the data and the exception to help debug the issue
                        System.out.println("Invalid data for parcel " + parcelID + " - " + e.getMessage());
                        continue;  // Skip this row if there is a format error
                    }

                    // Create a new Parcel object with all the necessary details
                    Parcel parcel = new Parcel(parcelID, length, width, height, weight, daysInDepot, "In Depot");
                    parcelTreeMap.put(parcelID, parcel);  // Add the parcel to the map

                    System.out.println("Added parcel: " + parcel);  // Debugging line to show the added parcel
                } else {
                    System.out.println("Invalid data (wrong number of columns): " + line);  // Debugging line for invalid rows
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Debugging to check how many parcels are loaded
        System.out.println("Loaded " + parcelTreeMap.size() + " parcels.");
    }






    // Save parcels from the TreeMap to a file
    public void saveParcels(String fileName) {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, Parcel> entry : parcelTreeMap.entrySet()) {
                Parcel parcel = entry.getValue();
                bufferedWriter.write(parcel.toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Find a parcel by ID
    public Parcel getParcel(String parcelID) {
        return parcelTreeMap.get(parcelID);
    }

    // Add new parcel
    public void addParcel(Parcel parcel) {
        parcelTreeMap.put(parcel.getParcelID(), parcel);
    }

    // Update parcel status and days in depot
    public void updateParcel(String parcelID, int daysInDepot, String status) {
        Parcel parcel = parcelTreeMap.get(parcelID);
        if (parcel != null) {
            parcel.setDaysInDepot(daysInDepot);
            parcel.setStatus(status);
        }
    }

    public Collection<Parcel> getAllParcels() {
        return parcelTreeMap.values();
    }

}

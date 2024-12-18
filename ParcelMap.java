import java.util.HashMap;
import java.util.Map;

public class ParcelMap {

    private Map<String, Parcel> parcels;

    public ParcelMap(Map<String, Parcel> parcels) {
        this.parcels = new HashMap<>();
    }
}

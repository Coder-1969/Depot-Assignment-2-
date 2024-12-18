public class Parcel {

    private String parcelID;
    private String dimensions;
    private double weight;
    private int daysInDepot;
    private String status;


    public Parcel(String parcelID, String dimensions, double weight, int daysInDepot, String status) {
        this.parcelID = parcelID;
        this.dimensions = dimensions;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = status;
    }

    public String getParcelID() {
        return parcelID;
    }

    public String getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public int getDaysInDepot() {
        return daysInDepot;
    }

    public String getStatus() {
        return status;
    }

    public void setDaysInDepot(int daysInDepot) {
        this.daysInDepot = daysInDepot;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

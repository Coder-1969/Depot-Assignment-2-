public class Parcel {
    private String parcelID;
    private double length;
    private double width;
    private double height;
    private double weight;
    private int daysInDepot;
    private String status;

    public Parcel(String parcelID, double length, double width, double height, double weight, int daysInDepot, String status) {
        this.parcelID = parcelID;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.daysInDepot = daysInDepot;
        this.status = "In Depot";
    }

    public String getParcelID() {
        return parcelID;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
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

    @Override
    public String toString() {
        return parcelID + "," + length + "," + width + "," + height + "," + weight + "," + daysInDepot + "," + status;
    }
}

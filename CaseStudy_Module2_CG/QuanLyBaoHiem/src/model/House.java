package model;

public class House {
    private String ownerName;
    private String address;
    private double houseValue;
    private int yearOfValuation;

    public House(String ownerName, String address, double houseValue, int yearOfValuation) {
        setOwnerName(ownerName);
        setAddress(address);
        setHouseValue(houseValue);
        setYearOfValuation(yearOfValuation);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getHouseValue() {
        return houseValue;
    }

    public void setHouseValue(double houseValue) {
        this.houseValue = houseValue;
    }

    public int getYearOfValuation() {
        return yearOfValuation;
    }

    public void setYearOfValuation(int yearOfValuation) {
        this.yearOfValuation = yearOfValuation;
    }
}

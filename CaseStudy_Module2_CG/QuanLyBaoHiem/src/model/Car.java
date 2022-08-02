package model;

public class Car {
    private String make;
    private String model;
    private String registration;
    private int year;
    private double originalPrice;

    public Car(String make, String model, String registration, int year, double originalPrice) {
        setMake(make);
        setModel(model);
        setRegistration(registration);
        setYear(year);
        setOriginalPrice(originalPrice);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }
}

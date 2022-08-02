package model;

import java.text.DecimalFormat;

public class InsuredCar extends Car implements Insured {
    private String name;
    private int age;
    private int rate;

    public InsuredCar(String make, String model, String registration, int year, double originalPrice, String name,
            int rate, int age) {
        super(make, model, registration, year, originalPrice);
        setName(name);
        setRate(rate);
        setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public double getInsuranceValue(int year) {
        double insuranceValue = getOriginalPrice();
        for (int i = 0; i < (year - getYear()); i++) {
            insuranceValue = 0.9 * insuranceValue;
        }
        return insuranceValue;
    }

    @Override
    public double getInsurancePremium(int year) {
        double insurancePremium = 0.03 * getInsuranceValue(year);
        if (age < 25) {
            insurancePremium = 1.2 * insurancePremium;
        }

        for (int i = 2; i <= rate; i++) {
            insurancePremium = 1.1 * insurancePremium;
        }

        return insurancePremium;
    }

    @Override
    public void outputPolicyDetails(int year) {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Chi phi bao hiem xe la: " + df.format(getInsurancePremium(year)));
        System.out.println("Gia tri bao hiem xe la: " + df.format(getInsuranceValue(year)));
    }
}

package model;

import java.text.DecimalFormat;

public class InsuredHouse extends House implements Insured {

    private int ownerAge;
    private double contentValue;

    public InsuredHouse(String ownerName, int ownerAge, String address, double houseValue, int yearOfValuation,
            double contentValue) {
        super(ownerName, address, houseValue, yearOfValuation);
        setOwnerAge(ownerAge);
        setContentValue(contentValue);
    }

    public int getOwnerAge() {
        return ownerAge;
    }

    public void setOwnerAge(int ownerAge) {
        this.ownerAge = ownerAge;
    }

    public double getContentValue() {
        return contentValue;
    }

    public void setContentValue(double contentValue) {
        this.contentValue = contentValue;
    }

    @Override
    public double getInsuranceValue(int year) {
        double insuranceValue = getHouseValue();
        int count = year - getYearOfValuation();
        for (int i = 0; i < count; i++) {
            insuranceValue = 1.05 * insuranceValue;
        }
        return insuranceValue + getContentValue();
    }

    @Override
    public double getInsurancePremium(int year) {
        double insurancePremium = 0.005 * getInsuranceValue(year);
        if (ownerAge > 65) {
            insurancePremium = 0.9 * insurancePremium;
        }
        return insurancePremium;
    }

    @Override
    public void outputPolicyDetails(int year) {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Chi phi bao hiem nha la: " + df.format(getInsurancePremium(year)));
        System.out.println("Gia tri bao hiem nha la: " + df.format(getInsuranceValue(year)));
    }
}

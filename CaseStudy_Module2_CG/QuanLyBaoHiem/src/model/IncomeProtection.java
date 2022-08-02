package model;

import java.text.DecimalFormat;

public class IncomeProtection implements Insured {

    private String nameCustomer;
    private int ageCustomer;
    private double currentIncome;

    public IncomeProtection(String nameCustomer, int ageCustomer, double currentIncome) {
        setNameCustomer(nameCustomer);
        setAgeCustomer(ageCustomer);
        setCurrentIncome(currentIncome);
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public int getAgeCustomer() {
        return ageCustomer;
    }

    public void setAgeCustomer(int ageCustomer) {
        this.ageCustomer = ageCustomer;
    }

    public double getCurrentIncome() {
        return currentIncome;
    }

    public void setCurrentIncome(double currentIncome) {
        this.currentIncome = currentIncome;
    }

    @Override
    public double getInsuranceValue(int year) {

        return (65 - ageCustomer) * currentIncome;
    }

    @Override
    public double getInsurancePremium(int year) {
        return getInsuranceValue(year) / 10000;
    }

    @Override
    public void outputPolicyDetails(int year) {
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println("Chi phi bao hiem thu nhap la: " + df.format(getInsurancePremium(year)));
        System.out.println("Gia tri bao hiem thu nhap la: " + df.format(getInsuranceValue(year)));
    }
}

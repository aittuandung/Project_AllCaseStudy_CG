package model;

public interface Insured {
    double getInsuranceValue(int year);

    double getInsurancePremium(int year);

    void outputPolicyDetails(int year);
}

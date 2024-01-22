package com.bank.model;

public class SavingsDTO extends ProductDTO {

    private double interestRate;     //금리
    private long savingsAmount;

    public SavingsDTO() {
    }

    public SavingsDTO(int type, int period, double interestRate, long savingsAmount) {
        super(type, period);
        this.interestRate = interestRate;
        this.savingsAmount = savingsAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getSavingsAmount() {
        return savingsAmount;
    }

    public void setSavingsAmount(long savingsAmount) {
        this.savingsAmount = savingsAmount;
    }

    @Override
    public String toString() {
        return "SavingsDTO{" +
                "interestRate=" + interestRate +
                ", savingsAmount=" + savingsAmount +
                '}';
    }
}

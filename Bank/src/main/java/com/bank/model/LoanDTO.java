package com.bank.model;

public class LoanDTO extends ProductDTO {

    private double interestRate;     //이자율
    private long loanAmount;

    public LoanDTO() {
    }

    public LoanDTO(double interestRate, long loanAmount) {
        this.interestRate = interestRate;
        this.loanAmount = loanAmount;
    }

    public LoanDTO(int type, int period, double interestRate, long loanAmount) {
        super(type, period);
        this.interestRate = interestRate;
        this.loanAmount = loanAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(long loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Override
    public String toString() {
        return "LoanDTO{" +
                "interestRate=" + interestRate +
                ", loanAmount=" + loanAmount +
                '}';
    }
}

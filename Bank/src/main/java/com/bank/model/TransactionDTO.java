package com.bank.model;

import java.util.Date;

public class TransactionDTO implements java.io.Serializable{
    private Date date;
    private String id;
    private String transaction;
    private long amount;

    public TransactionDTO() {
    }

    public TransactionDTO(Date date, String id, String transaction, long amount) {
        this.date = date;
        this.id = id;
        this.transaction = transaction;
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "date=" + date +
                ", id='" + id + '\'' +
                ", transaction='" + transaction + '\'' +
                ", amount=" + amount +
                '}';
    }
}

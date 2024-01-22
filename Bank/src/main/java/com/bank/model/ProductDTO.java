package com.bank.model;

public class ProductDTO {

    private int type;               //상품타입 (1.적금 / 2.대출)
    private int period;             //가입기간, 대출기한

    public ProductDTO() {
    }

    public ProductDTO(int type, int period) {
        this.type = type;
        this.period = period;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type=" + type +
                ", period=" + period +
                '}';
    }
}

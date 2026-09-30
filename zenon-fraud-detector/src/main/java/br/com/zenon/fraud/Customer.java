package br.com.zenon.fraud;

import java.math.BigDecimal;

public class Customer{
        String name;
        BigDecimal oldbalance;
        BigDecimal newbalance;

    public Customer(String name, BigDecimal oldbalance, BigDecimal newbalance) {
        this.name = name;
        this.oldbalance = oldbalance;
        this.newbalance = newbalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getOldbalance() {
        return oldbalance;
    }

    public void setOldbalance(BigDecimal oldbalance) {
        this.oldbalance = oldbalance;
    }

    public BigDecimal getNewbalance() {
        return newbalance;
    }

    public void setNewbalance(BigDecimal newbalance) {
        this.newbalance = newbalance;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", oldbalance=" + oldbalance +
                ", newbalance=" + newbalance +
                '}';
    }
}

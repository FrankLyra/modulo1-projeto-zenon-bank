package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(
        int step,
        TransactionType type,
        BigDecimal amount,
        Customer origin,
        Customer destination,
        int isFraud,
        int isFlaggedFraud
) {
    public Transaction(int step, TransactionType type, BigDecimal amount, Customer origin, Customer destination, int isFraud, int isFlaggedFraud) {
        this.step = step;
        this.type = type;
        this.amount = amount;
        this.origin = origin;
        this.destination = destination;
        this.isFraud = isFraud;
        this.isFlaggedFraud = isFlaggedFraud;
    }
}


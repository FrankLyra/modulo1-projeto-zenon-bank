package br.com.zenon.fraud;

import java.math.BigDecimal;

public record CustomerOrig(

        String name,

        BigDecimal oldBalance,

        BigDecimal newBalance

) {

    public CustomerOrig(String name, BigDecimal oldBalance, BigDecimal newBalance) {
        this.name = name;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
    }
}

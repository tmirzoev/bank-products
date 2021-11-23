package com.github.tmirzoev.bank.products;

import java.math.BigDecimal;
import java.util.Currency;

class DefaultAccount implements Account {

    private final Currency currency;
    private BigDecimal balance;

    DefaultAccount(Currency currency, BigDecimal balance) {
        this.currency = currency;
        this.balance = balance;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) throws IllegalArgumentException {
        if (amount.signum() < 0){
            throw new IllegalArgumentException("Deposit amount is negative, should be >= 0");
        }
        balance = balance.add(amount);
        return balance;
    }

    @Override
    public BigDecimal withdraw(BigDecimal amount) throws IllegalArgumentException{
        if (amount.signum() < 0) {
            throw new IllegalArgumentException("Withdrawal amount is negative, should be >= 0");
        }
        balance = balance.subtract(amount);
        return balance;
    }

    @Override
    public BigDecimal close() {
        var remainder = this.balance;
        this.balance = BigDecimal.ZERO;
        return remainder;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }
}

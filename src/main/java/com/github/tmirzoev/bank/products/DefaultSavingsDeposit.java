package com.github.tmirzoev.bank.products;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Currency;

class DefaultSavingsDeposit implements SavingsDeposit {

    private Account account;
    @Getter
    private final String name;

    DefaultSavingsDeposit(String name, Account account) {
        this.name = name;
        this.account = account;
    }

    @Override
    public BigDecimal getBalance() {
        return account.getBalance();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount) {
        return account.deposit(amount);
    }

    @Override
    public Currency getCurrency() {
        return account.getCurrency();
    }

    @Override
    public BigDecimal close() {
        return account.close();
    }
}

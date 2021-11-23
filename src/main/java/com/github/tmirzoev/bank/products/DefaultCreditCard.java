package com.github.tmirzoev.bank.products;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Currency;

@ToString
@EqualsAndHashCode
public class DefaultCreditCard implements CreditCard {
    private final Account account;
    @Getter
    private final String name;
    private final BigDecimal interest;

    DefaultCreditCard(String name, Account account, BigDecimal interest) {
        this.name = name;
        this.interest = interest;
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
    public BigDecimal withdraw(BigDecimal amount) {
        return account.withdraw(amount);
    }

    @Override
    public Currency getCurrency() {
        return account.getCurrency();
    }

    // A naive implementation for the sake of test project
    @Override
    public BigDecimal getDebt() {
        return account.getBalance().compareTo(BigDecimal.ZERO) < 0 ? account.getBalance().abs() : BigDecimal.ZERO;
    }
}

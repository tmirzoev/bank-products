package com.github.tmirzoev.bank.products;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Currency;

@ToString
@EqualsAndHashCode
class DefaultDebitCard implements DebitCard {
    private final Account account;
    @Getter
    private final String name;

    DefaultDebitCard(String name, Account account) {
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
    public BigDecimal withdraw(BigDecimal amount) {
        return account.withdraw(amount);
    }

    @Override
    public Currency getCurrency() {
        return account.getCurrency();
    }
}

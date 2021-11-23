package com.github.tmirzoev.bank.products;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;

interface Account {

    static DefaultAccount getDefaultAccount(@NonNull Currency currency, @NonNull BigDecimal amount) {
        return new DefaultAccount(currency, amount);
    }

    BigDecimal getBalance();

    BigDecimal deposit(BigDecimal amount) throws IllegalArgumentException;

    BigDecimal withdraw(BigDecimal amount) throws IllegalArgumentException;

    BigDecimal close();

    Currency getCurrency();
}

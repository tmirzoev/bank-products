package com.github.tmirzoev.bank.products;

import java.math.BigDecimal;
import java.util.Currency;

public interface Product {

    BigDecimal getBalance();

    BigDecimal deposit(BigDecimal amount) throws IllegalArgumentException;

    Currency getCurrency();

    String getName();
}

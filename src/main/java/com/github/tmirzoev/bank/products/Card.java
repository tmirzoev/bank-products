package com.github.tmirzoev.bank.products;

import java.math.BigDecimal;

public interface Card extends Product {

    BigDecimal withdraw(BigDecimal amount) throws IllegalArgumentException;
}

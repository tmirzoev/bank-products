package com.github.tmirzoev.bank.products;

import java.math.BigDecimal;

public interface Deposit extends Product {

    BigDecimal close();

}

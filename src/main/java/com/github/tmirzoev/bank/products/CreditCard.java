package com.github.tmirzoev.bank.products;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;

public interface CreditCard extends Card {

    static CreditCard getDefaultCreditCard(@NonNull String name, @NonNull Currency currency, @NonNull BigDecimal amount, BigDecimal interest) {
        var account = Account.getDefaultAccount(currency, amount);
        return new DefaultCreditCard(name, account, interest);
    }

    BigDecimal getDebt();
}

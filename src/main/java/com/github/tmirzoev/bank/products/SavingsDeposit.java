package com.github.tmirzoev.bank.products;

import lombok.NonNull;

import java.math.BigDecimal;
import java.util.Currency;

public interface SavingsDeposit extends Deposit {
    static SavingsDeposit getDefaultSavingsDeposit(@NonNull String name, @NonNull Currency currency, @NonNull BigDecimal amount) {
        var account = Account.getDefaultAccount(currency, amount);
        return new DefaultSavingsDeposit(name, account);
    }
}

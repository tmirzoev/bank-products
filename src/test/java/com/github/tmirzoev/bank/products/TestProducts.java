package com.github.tmirzoev.bank.products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class TestProducts {

    @ParameterizedTest
    @MethodSource("productDataProvider")
    void testDepositMoneyToProduct(Product product) {
        var balance = product.getBalance();
        var amount = new BigDecimal("123.44678");
        var expected = balance.add(amount);
        var actual = product.deposit(amount);
        Assertions.assertEquals(expected, actual, "Result from deposit call differs from expected.");
        Assertions.assertEquals(expected, product.getBalance(), "Balance after deposit differs from expected.");
    }

    @ParameterizedTest
    @MethodSource("productDataProvider")
    void testDepositNegativeAmountFails(Product product) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> product.deposit(new BigDecimal("-1")),
                "Attempt to deposit negative amount succeeded.");
    }

    @ParameterizedTest
    @MethodSource("cardDataProvider")
    void testWithdrawMoneyFromCard(Card card) {
        var balance = card.getBalance();
        var amount = new BigDecimal("0.44678");
        var expected = balance.subtract(amount);
        var actual = card.withdraw(amount);
        Assertions.assertEquals(expected, actual, "Result from withdraw call differs from expected.");
        Assertions.assertEquals(expected, card.getBalance(), "Balance after withdrawal differs from expected.");
    }

    @ParameterizedTest
    @MethodSource("cardDataProvider")
    void testWithdrawNegativeAmountFails(Card card) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> card.withdraw(new BigDecimal("-1.1")),
                "Attempt to withdraw negative amount succeeded.");
    }


    @ParameterizedTest
    @MethodSource("creditCardDataProvider")
    void testGetCreditCardDebtAfterDeposit(CreditCard creditCard) {
        var expectedBeforeOp = calculateDebtForCard(creditCard);
        Assertions.assertEquals(expectedBeforeOp, creditCard.getDebt(), "Debt for card before deposit operation differs from expected.");

        var amount = new BigDecimal("0.44678");
        creditCard.deposit(amount);

        var expectedAfterOp = calculateDebtForCard(creditCard);
        Assertions.assertEquals(expectedAfterOp, creditCard.getDebt(), "Debt for card after deposit operation differs from expected.");

    }

    @ParameterizedTest
    @MethodSource("creditCardDataProvider")
    void testGetCreditCardDebtAfterWithdraw(CreditCard creditCard) {
        var expectedBeforeOp = calculateDebtForCard(creditCard);
        Assertions.assertEquals(expectedBeforeOp, creditCard.getDebt(), "Debt for card before waithdrawal operation differs from expected.");

        var amount = new BigDecimal("0.44678");
        creditCard.withdraw(amount);

        var expectedAfterOp = calculateDebtForCard(creditCard);
        Assertions.assertEquals(expectedAfterOp, creditCard.getDebt(), "Debt for card after withdrawal operation differs from expected.");

    }

    private BigDecimal calculateDebtForCard(Card card) {
        return card.getBalance().compareTo(BigDecimal.ZERO) < 0 ? card.getBalance().abs() : BigDecimal.ZERO;

    }

    @ParameterizedTest
    @MethodSource("savingsDepositDataProvider")
    void testSavingsDepositClose(SavingsDeposit savingsDeposit) {
        var expected = savingsDeposit.getBalance();
        var actual = savingsDeposit.close();

        Assertions.assertEquals(expected, actual, "Remainder for closing deposit call differs from expected.");
        Assertions.assertEquals(BigDecimal.ZERO, savingsDeposit.getBalance().stripTrailingZeros(), "Balance after closing deposit is not zero.");

    }


    private static List<Product> productDataProvider() {
        var result = new ArrayList<Product>();
        result.addAll(cardDataProvider());
        result.addAll(savingsDepositDataProvider());
        return result;
    }

    private static List<Card> cardDataProvider() {
        var result = new ArrayList<Card>();
        result.addAll(creditCardDataProvider());
        result.addAll(debitCardDataProvider());
        return result;
    }

    private static List<SavingsDeposit> savingsDepositDataProvider() {
        return List.of(
                SavingsDeposit.getDefaultSavingsDeposit("test-SD-1", Currency.getInstance("RUB"), BigDecimal.ZERO),
                SavingsDeposit.getDefaultSavingsDeposit("test-SD-2", Currency.getInstance("RUB"), BigDecimal.ONE),
                SavingsDeposit.getDefaultSavingsDeposit("test-SD-3", Currency.getInstance("RUB"), new BigDecimal("-146.123"))
        );
    }

    private static List<CreditCard> creditCardDataProvider() {
        return List.of(
                CreditCard.getDefaultCreditCard("test-CC-1", Currency.getInstance("RUB"), BigDecimal.ZERO, BigDecimal.TEN),
                CreditCard.getDefaultCreditCard("test-CC-2", Currency.getInstance("RUB"), BigDecimal.ONE, new BigDecimal("12.456")),
                CreditCard.getDefaultCreditCard("test-CC-3", Currency.getInstance("RUB"), BigDecimal.TEN.negate(), BigDecimal.valueOf(146))
        );
    }

    private static List<DebitCard> debitCardDataProvider() {
        return List.of(
                DebitCard.getDefaultDebitCard("test-DC-1", Currency.getInstance("RUB"), BigDecimal.ZERO),
                DebitCard.getDefaultDebitCard("test-DC-2", Currency.getInstance("EUR"), BigDecimal.TEN),
                DebitCard.getDefaultDebitCard("test-DC-3", Currency.getInstance("RUB"), BigDecimal.valueOf(-15)),
                DebitCard.getDefaultDebitCard("test-DC-4", Currency.getInstance("USD"), new BigDecimal("12.11")),
                DebitCard.getDefaultDebitCard("test-DC-5", Currency.getInstance("USD"), new BigDecimal("-12.11"))

        );
    }


}

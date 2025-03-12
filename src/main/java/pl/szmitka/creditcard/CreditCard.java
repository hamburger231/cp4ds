package pl.szmitka.creditcard;

import java.math.BigDecimal;

public class CreditCard {

    public static final int CREDIT_THRESHOLD = 100;
    private final String cardNumber;
    private BigDecimal creditLimit;
    private BigDecimal balance;

    public CreditCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Object getNumber() {
        return cardNumber;
    }

    public BigDecimal getBalance() {
        return balance;

    }
    public void assignCredit(BigDecimal credit) {
        if (isCreditBelowThreshold(credit)) {
            throw new CreditBelowThreshholdException();
        }
        if (isCreditAlreadyAssigned()) {
            throw new CreditCantBeAssignedTwice();
        }
        this.creditLimit = credit;
        this.balance = credit;
    }

    private boolean isCreditAlreadyAssigned() {
        return this.creditLimit != null;
    }

    private static boolean isCreditBelowThreshold(BigDecimal credit) {
        return BigDecimal.valueOf(CREDIT_THRESHOLD).compareTo(credit) > 0;
    }

    public void withdraw(BigDecimal money) {
        if (!canAfford(money)) {
            throw new NotEnoughMoneyException();
        }
        this.balance = this.balance.subtract(money);
    }

    private boolean canAfford(BigDecimal money) {
        return this.balance.subtract(money).compareTo(BigDecimal.ZERO) < 0;
    }
}

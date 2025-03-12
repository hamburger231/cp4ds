package pl.szmitka.creditcard;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
public class CreditCardTest {
    @Test
    void creditCardIsIndentifiedWithNumber() {
        //Arrange   //  Given   //  Input
        var number = "1234-5678";
        //Act       //  When    //  Call

        var card = new CreditCard(number);
        //Assert    //  Then    //  Output
        assertEquals(
                "1234-5678",
                card.getNumber());

    }
    @Test
    void itAllowsToAssignCreditLimit() {
        //Arrange   //  Given   //  Input
        var card = new CreditCard("1234-5678");
        //Act       //  When    //  Call
        card.assignCredit(BigDecimal.valueOf(1500));
        //Assert    //  Then    //  Output
        assertEquals(BigDecimal.valueOf(1500), card.getBalance());
    }
    @Test
    void creditLimitCantBeLowerThanCertainThreshhold(){
        var card = new CreditCard("1234-4567");
        try {
            card.assignCredit(BigDecimal.valueOf(90));
            fail("Exception should be thrown");
        }  catch (CreditBelowThreshholdException e) {
            assertTrue(true);
        }
    }
    @Test
    void creditLimitCantBeLowerThanCertainThreshholdV2(){
        var card = new CreditCard("1234-4567");
        assertThrows(
                CreditBelowThreshholdException.class, () -> card.assignCredit(BigDecimal.valueOf(99))
        );
        assertDoesNotThrow(
                () -> card.assignCredit(BigDecimal.valueOf(100))
        );
    }
    @Test
    void creditCantBeAssignedTwice(){
        var card = new CreditCard("1234-4567");
        card.assignCredit(BigDecimal.valueOf(1000));

        assertThrows(
                CreditCantBeAssignedTwice.class,
                () -> card.assignCredit(BigDecimal.valueOf(200))
        );
    }
    @Test
    void iamAbleToWithdrawSomeMoney() {
        var card = new CreditCard("1234-4567");
        card.assignCredit(BigDecimal.valueOf(1500));

        card.withdraw(BigDecimal.valueOf(100));
        card.withdraw(BigDecimal.valueOf(100));
        card.withdraw(BigDecimal.valueOf(100));

        assertEquals(
                BigDecimal.valueOf(1200),
                card.getBalance()
        );
    }
    @Test
    void iamAbleToWithdrawSomeMoneyV2() {
        var card = new CreditCard("1234-4567");
        card.assignCredit(BigDecimal.valueOf(1500));

        assertThrows(
                NotEnoughMoneyException.class,
                () -> card.withdraw(BigDecimal.valueOf(1600))
        );
    }
}

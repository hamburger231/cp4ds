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
}

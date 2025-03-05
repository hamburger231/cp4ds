package pl.szmitka.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class HelloTest {
    @Test
    void myFirstTest() {
        int a = 2;
        int b = 3;

        int result = a+b;

        assert result == 5;
    }
    @Test
    void myFirstFailingTest() {
        int a = 2;
        int b = 3;

        int result = a+b+2;

        assert result == 5;
    }
    @Test
    void moreReadableAssertion() {
        assertTrue(true, "Your condition is not true anymore");
    }
}

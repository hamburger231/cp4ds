package pl.szmitka.ecommerce.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions.*;
import pl.szmitka.ecommerce.productcatalog.ArrayListProductStorage;
import pl.szmitka.ecommerce.productcatalog.ProductCatalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesTest {
    ProductCatalog catalog;
    @BeforeEach
    void setup() {
        catalog = new ProductCatalog(new ArrayListProductStorage());
    }
@Test
    void itShowsEmptyOffer() {
    SalesFacade sales = thereIsSalesModuleUnderTest();
    String customerId = thereIsCustomer("Dawid");
    Offer offer = sales.getCurentOffer(customerId);
    assertEquals(BigDecimal.ZERO, offer.getTotal());
}

    private String thereIsCustomer(String customerName) {
        return String.format("customer__%s", customerName);
    }
    private SalesFacade thereIsSalesModuleUnderTest() {
        return new SalesFacade();
    }
    @Test
    void itAllowsToCollectProducts() {
    SalesFacade sales = thereIsSalesModuleUnderTest();
    String customerId = thereIsCustomer("Dawid");
    String productId = thereIsProduct("Product X", BigDecimal.valueOf(10));

    sales.addToCart(customerId,productId);
    Offer offer = sales.getCurentOffer(customerId);
    assertEquals(BigDecimal.valueOf(10), offer.getTotal());
    }
    @Test
    void itAllowsToCollectProductsByCustomersSeperately() {
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customer1 = thereIsCustomer("Dawid");
        String customer2 = thereIsCustomer("Kuba");
        String productId = thereIsProduct("Product X", BigDecimal.valueOf(10));

        sales.addToCart(customer1,productId);
        sales.addToCart(customer2,productId);
        sales.addToCart(customer2,productId);

        Offer offer1 = sales.getCurentOffer(customer1);
        Offer offer2 = sales.getCurentOffer(customer2);

        assertEquals(BigDecimal.valueOf(10), offer1.getTotal());
        assertEquals(BigDecimal.valueOf(20), offer2.getTotal());
    }
    @Test
    void offerAcceptance() {
        SalesFacade sales = thereIsSalesModuleUnderTest();
        String customerId = thereIsCustomer("Dawid");
        String productId = thereIsProduct("Product X", BigDecimal.valueOf(10));

        sales.addToCart(customerId,productId);
        Offer offer = sales.getCurentOffer(customerId);

        ReservationDetails details =
                sales.acceptOffer(
                        new AcceptOfferCommand()
                                .setFname("Kuba")
                                .setLname("Dawid")
                                .setEmail("dszmitka@google.pl")
        );
    }

    private String thereIsProduct(String name, BigDecimal price) {
        var id = catalog.createProduct(name, "desc");
        catalog.changePrice(id, price);
        return id;
    }
}

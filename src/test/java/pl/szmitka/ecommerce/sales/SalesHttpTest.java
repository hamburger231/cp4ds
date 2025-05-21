package pl.szmitka.ecommerce.sales;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import pl.szmitka.ecommerce.productcatalog.ProductCatalog;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalesHttpTest {
    @LocalServerPort
    int port;
    @Autowired
    TestRestTemplate http;
    @Autowired
    ProductCatalog catalog;

    @Test
    void checkoutHappyPath(){
        String productId = thereIsProduct("1234", BigDecimal.valueOf(11));
        var toBeCalledURL = getBaseUrl(String.format("/api/add-product/%s", productId));
        
        http.postForEntity(toBeCalledURL, null, null);
        http.postForEntity(toBeCalledURL, null, null);

        var toBeCalledURLOffer = getBaseUrl("/api/current-offer");
        ResponseEntity<Offer> offerHttp = http.getForEntity(toBeCalledURLOffer,Offer.class);
        assertEquals(BigDecimal.valueOf(22), offerHttp.getBody().getTotal());
    }

    private String thereIsProduct(String name, BigDecimal productPrice) {
        var id = catalog.createProduct(name, "nice one");
        catalog.changePrice(id, productPrice);
        return id;
    }

    private String getBaseUrl(String endpoint) {
        return String.format("http://localhost:%s%s", port, endpoint);
    }
}

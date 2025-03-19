package pl.szmitka.productcatalog;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCatalogTest {
    @Test
    void itAllowsToListAllProducts() {
        ProductCatalog catalog = thereIsProductCatalog();

        List<Product> products = catalog.allProducts();

        assertTrue(products.isEmpty());
    }
    @Test
    void itAllowsToCreateProducts() {
        ProductCatalog catalog = thereIsProductCatalog();
        catalog.createProduct("Lego set 8083", "Nice one");
        List<Product> products = catalog.allProducts();
        assertFalse(products.isEmpty());
    }
    @Test
    void createProductIsIdentifiable(){
        ProductCatalog catalog = thereIsProductCatalog();

        String productId1 = catalog.createProduct("Lego set 8083", "Nice one");
        String productId2 = catalog.createProduct("Lego set 8083", "Nice one");

        assertNotEquals(productId1, productId2);
    }
    @Test
    void itLoadsProductsById() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId1 = catalog.createProduct("Lego set 8083", "Nice one");
        Product loaded = catalog.loadProductById(productId1);
        assertEquals(productId1, loaded.getId());
        assertEquals("Lego set 8083", loaded.getName());
        assertEquals("Nice one", loaded.getDescription());
    }
    @Test
    void allowsToApplyPrice() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.createProduct("Lego set 8083", "Nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(100.10));
        catalog.changeImage(productId, "");

        Product loaded = catalog.loadProductById(productId);
        assertEquals(BigDecimal.valueOf(100.10), loaded.getPrice());
    }
    @Test
    void allowsToApplyImage() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.createProduct("Lego set 8083", "Nice one");

        catalog.changeImage(productId, "siema");

        Product loaded = catalog.loadProductById(productId);
        assertEquals("siema", loaded.getImage());
    }
    @Test
    void denyToApplyPriceThatViolateMinimumRange() {
        ProductCatalog catalog = thereIsProductCatalog();

        String productId = catalog.createProduct("Lego set 8083", "Nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(100.10));

        Product loaded = catalog.loadProductById(productId);
        assertEquals(BigDecimal.valueOf(100.10), loaded.getPrice());
    }
    private ProductCatalog thereIsProductCatalog() {
        return new ProductCatalog();
    }
}

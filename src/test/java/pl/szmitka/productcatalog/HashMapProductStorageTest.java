package pl.szmitka.productcatalog;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapProductStorageTest {
    private ProductStorage thereIsStorage() {
        return new HashMapProductStorage();
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "test it", "siemaneczko");
    }

    @Test
    void itSaveAndLoadProduct() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);
        var loaded = storage.loadProductById(product.getId());

        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getDescription(), loaded.getDescription());
    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);

        List<Product> all = storage.allProducts();

        assertEquals(1, all.size());
    }
}

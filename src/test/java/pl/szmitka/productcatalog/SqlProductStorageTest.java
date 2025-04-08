package pl.szmitka.productcatalog;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlProductStorageTest {
    private ProductStorage thereIsStorage() {
        return SqlProductStorage();
    }

    private Product thereIsProduct() {
        return null;
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

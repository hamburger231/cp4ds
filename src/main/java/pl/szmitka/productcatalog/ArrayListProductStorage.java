package pl.szmitka.productcatalog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListProductStorage implements ProductStorage {
    private List<Product> products;

    public ArrayListProductStorage() {
        this.products = new ArrayList<>();
    }
    @Override
    public List<Product> allProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public void save(Product newProduct) {
        this.products.add(newProduct);
    }
    @Override
    public Product loadProductById(String productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst()
                .get();
    }
}

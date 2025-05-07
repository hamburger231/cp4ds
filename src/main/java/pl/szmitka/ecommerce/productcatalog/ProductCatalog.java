package pl.szmitka.ecommerce.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalog {
    ProductStorage productStorage;
    public ProductCatalog(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public List<Product> allProducts() {
        return productStorage.allProducts();
    }

    public String createProduct(String name, String description) {
        var uuid = UUID.randomUUID();

        var newProduct = new Product(
                uuid,
                name,
                description
        );
        this.productStorage.save(newProduct);

        return newProduct.getId();
    }

    public Product loadProductById(String productId) {
        return productStorage.loadProductById(productId);
    }

    public void changePrice(String productId, BigDecimal bigDecimal) {
        var product = loadProductById(productId);
        if (bigDecimal.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidPriceException();
        }
        product.changePrice(bigDecimal);
    }

    public void changeImage(String productId, String URL) {
        var product = productStorage.loadProductById(productId);
        product.setImage(URL);
    }
}

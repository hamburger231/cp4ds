package pl.szmitka.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID uuid;
    private final String name;
    private final String description;

    private BigDecimal price;

    private String URL;
    public Product(UUID uuid, String name, String description) {

        this.uuid = uuid;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return uuid.toString();
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setImage(String url) {
        this.URL = url;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getImage() {
        return this.URL;
    }
}

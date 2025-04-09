package pl.szmitka.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.szmitka.productcatalog.ArrayListProductStorage;
import pl.szmitka.productcatalog.ProductCatalog;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        System.out.println("It works");
    }
    @Bean
    ProductCatalog createMyProductCatalog() {
        var catalog = new ProductCatalog(
                new ArrayListProductStorage()
        );
        catalog.createProduct("Nice one 1", "nice one");
        catalog.createProduct("Nice one 2", "nice two");
        catalog.createProduct("Nice one 3", "nice three");
        return catalog;

    }
}

package pl.szmitka.ecommerce.productcatalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.szmitka.ecommerce.productcatalog.Product;
import pl.szmitka.ecommerce.productcatalog.ProductStorage;
import pl.szmitka.ecommerce.productcatalog.SqlProductStorage;

public class SqlProductStorageTest {
    private ProductStorage thereIsStorage() {
        return SqlProductStorage();
    }

    private ProductStorage SqlProductStorage() {
        return new SqlProductStorage(jdbcTemplate);
    }

    private Product thereIsProduct() {
        return new Product(UUID.randomUUID(), "test it", "siemaneczko");
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    @BeforeEach
    void setupDatabase() {
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS");
        var sql = """
                CREATE TABLE `product_catalog__products` (
                id VARCHAR(100) NOT NULL,
                name VARCHAR(100) NOT NULL,
                description VARCHAR(100) NOT NULL,
                price VARCHAR(100) NOT NULL,
                cover VARCHAR(100) NOT NULL,
                PRIMARY KEY(id)
                )
                """;
        jdbcTemplate.execute(sql);
    }
    @Test
    void itAllowsToInsertIntoTableWithArguments() {
        var insertSql = """
                INSERT INTO `product_catalog__products` (id,name,description)
                VALUES (?,?,?)
                """;
        jdbcTemplate.update(insertSql, "3366c04d-c2f6-4af2-8f37-36dd14235865", "product 1", "nice one");
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assert result == 1;
    }
    @Test
    void itAllowsToInsertIntoTableAsNamedParameters() {
        var insertSql = """
                INSERT INTO `product_catalog__products` (id,name,description)
                VALUES (:id, :name, :desc)
                """;
        Map<String, Object> params = new HashMap<>();
        params.put("id", "38fd61af-c9cb-4b12-a95e-88653c1d77e4");
        params.put("desc", "products");
        params.put("name", "nice product");

        var namedJdbc = new NamedParameterJdbcTemplate(jdbcTemplate);

        namedJdbc.update(insertSql, params);

        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog__products`", Integer.class);

        assert result == 1;
    }

    @Test
    void itAllowsToInsertIntoTable() {
        var sql = """
                INSERT INTO `product_catalog__products` (id, name, description)
                VALUES
                ('a66f63d3-3cec-4e50-8fa1-833be30dede5', 'product 1','nice desc'),
                ('a66f63d3-3cec-4e50-8fa1-833be30dede4', 'product 2','even nicer desc'),
                ;
                """;
        jdbcTemplate.execute(sql);
        var result = jdbcTemplate.queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assert result == 2;

    }

    @Test
    void itLoadsAllProducts() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();

        storage.save(product);

        List<Product> all = storage.allProducts();
        assertEquals(1, all.size());
    }
    @Test
    void itSaveAndLoadProduct() {
        Product product = thereIsProduct();
        ProductStorage storage = thereIsStorage();
        storage.save(product);
        var loaded = storage.loadProductById("123123123");
        assertEquals(product.getId(), loaded.getId());
        assertEquals(product.getDescription(), loaded.getDescription());
    }
}

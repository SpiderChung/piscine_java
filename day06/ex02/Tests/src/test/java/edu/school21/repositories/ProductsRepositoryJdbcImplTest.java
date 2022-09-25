package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepository repository;
    private EmbeddedDatabase dataSource;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "RitterSport", 148L),
            new Product(2L, "LemonTree", 1000L),
            new Product(3L, "Coffee", 220L),
            new Product(4L, "Snickers", 80L),
            new Product(5L, "Mars", 56L),
            new Product(6L, "Nail", 10L),
            new Product(7L, "Aqua Minerale", 66L),
            new Product(8L, "Essentuki", 70L),
            new Product(9L, "Kefir", 80L),
            new Product(10L, "Papaya", 500L));

    final List<Product> EXPECTED_DELETE_PRODUCTS = Arrays.asList(
            new Product(1L, "RitterSport", 148L),
            new Product(2L, "LemonTree", 1000L),
            new Product(3L, "Coffee", 220L),
            new Product(4L, "Snickers", 80L),
            new Product(5L, "Mars", 56L),
            new Product(6L, "Nail", 10L),
            new Product(7L, "Aqua Minerale", 66L),
            new Product(9L, "Kefir", 80L),
            new Product(10L, "Papaya", 500L));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(7L, "Aqua Minerale", 66L);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Straw", 2L);
    final Product EXPECTED_SAVED_PRODUCT = new Product(11L, "Meat", 1111L);

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).
                addScript("schema.sql").addScript("data.sql").build();
        repository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest() {
        List<Product> list = repository.findAll();
        Assertions.assertEquals(EXPECTED_FIND_ALL_PRODUCTS, list);
    }

    @Test
    public void findByIdTest() {
        Assertions.assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, repository.findById(7L).get());
        Assertions.assertEquals(Optional.empty(), repository.findById(99L));
        Assertions.assertEquals(Optional.empty(), repository.findById(null));
    }

    @Test
    public void saveTest() {
        repository.update(new Product(3L, "Straw", 2L));
        Assertions.assertEquals(EXPECTED_UPDATED_PRODUCT, repository.findById(3L).get());
    }

    @Test
    public void updateTest() {
        repository.save(new Product(11L, "Meat", 1111L));
        Assertions.assertEquals(EXPECTED_SAVED_PRODUCT, repository.findById(11L).get());
    }

    @Test
    public void deleteTest() {
        repository.delete(8L);
        Assertions.assertEquals(EXPECTED_DELETE_PRODUCTS, repository.findAll());
        Assertions.assertFalse(repository.findById(8L).isPresent());
    }

    @AfterEach
    void close() {
        dataSource.shutdown();
    }
}

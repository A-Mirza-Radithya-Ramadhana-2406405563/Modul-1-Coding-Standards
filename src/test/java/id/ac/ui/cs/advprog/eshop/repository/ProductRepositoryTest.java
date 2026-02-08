package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exception.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {
    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    public void setUp() {

    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product1.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9906");
        product1.setProductName("Sampo Cap Usep");
        product1.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdIfProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product savedProduct = productRepository.findById(product.getProductId());
        assertEquals(product, savedProduct);
    }

    @Test
    void testFindByIdIfProductDoesNotExist() {
        assertThrows(ProductNotFoundException.class, () -> {
           productRepository.findById("NO_ID");
        });
    }

    @Test
    void testEditProductIfProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductName("Sampo Cap");
        editedProduct.setProductQuantity(50);
        productRepository.edit(product.getProductId(), editedProduct);

        Product savedProduct = productRepository.findById(product.getProductId());
        assertEquals("Sampo Cap", savedProduct.getProductName());
        assertEquals(50, savedProduct.getProductQuantity());
    }

    @Test
    void testEditProductIfProductDoesNotExist() {
        Product editedProduct = new Product();
        editedProduct.setProductName("Sampo Cap");
        editedProduct.setProductQuantity(50);
        assertThrows(ProductNotFoundException.class, () -> {
            productRepository.edit("NO_ID", editedProduct);
        });
    }

    @Test
    void testDeleteProductIfProductExists() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);
        assertEquals(product, productRepository.findById(product.getProductId()));
        productRepository.delete(product.getProductId());
        assertThrows(ProductNotFoundException.class, () -> {
            productRepository.findById(product.getProductId());
        });
    }

    @Test
    void testDeleteProductIfProductDoesNotExist() {
        assertThrows(ProductNotFoundException.class, () -> {
            productRepository.delete("NO_ID");
        });
    }
}

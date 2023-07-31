package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MyRestController {

    List<Product> products = new ArrayList<>();

    @GetMapping("products")
    public List<Product> products() {
//        Product product1 = new Product();
//        product1.setProductId("1");
//        product1.setName("Iphone 11");
//        product1.setPrice(1000);
//        product1.setDescription("Brand new IPhone 11");
//
//        Product product2 = new Product();
//        product2.setProductId("2");
//        product2.setName("Iphone 12");
//        product2.setPrice(1100);
//        product2.setDescription("Used IPhone 12");
//
//        products.add(product1);
//        products.add(product2);

        return products;
    }

    @GetMapping("products/{productId}")
    public Product productById(@PathVariable("productId") String productId) {
        for (Product product: products) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }

        return null;
    }

    @PostMapping("products")
    public Product addProduct(@RequestBody Product product) {
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        products.add(product);
        return product;
    }

    @PutMapping("products/{productId}")
    public Product updateProduct(@PathVariable("productId") String productId, @RequestBody Product updatedProduct) {
        for (Product product: products) {
            if (product.getProductId().equals(productId)) {
                product.setName(updatedProduct.getName());
                product.setPrice(updatedProduct.getPrice());
                product.setDescription(updatedProduct.getDescription());
                return product;
            }
        }
        throw new IllegalArgumentException("Item with ID " + productId + " not found");
    }

    @DeleteMapping("products/{productId}")
    public String deleteProductById(@PathVariable("productId") String productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                products.remove(i);
                return "Deleted product with id = " + productId;
            }
        }

        throw new IllegalArgumentException("Cannot delete a product with id = " + productId);
    }

    @GetMapping("hello/world")
    public String helloWorld() {
        return "Hello world!";
    }

    @GetMapping("hello/friend")
    public String helloFriend() {
        return "Hello friend!";
    }
}

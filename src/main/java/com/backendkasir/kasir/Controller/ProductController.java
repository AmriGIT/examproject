/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backendkasir.kasir.Controller;

import com.backendkasir.kasir.Entity.Product;
import com.backendkasir.kasir.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rahma
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProduct() {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(product);
        }
        return ResponseEntity.ok(productList);
    }

//    @GetMapping("keranjangs/product/{id}")
//    public ResponseEntity<Product> getKeranjangProductById(@PathVariable(name = "id") Integer id) {
//        Optional<Product> optionalProduct = productRepository.findById(id);
//        if (!optionalProduct.isEmpty()) {
//            return ResponseEntity.ok(optionalProduct.get());
//        }
//        return ResponseEntity.badRequest().body(null);
//    }

    @GetMapping("product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isEmpty()) {
            return ResponseEntity.ok(optionalProduct.get());
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/product/")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/product/")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        if (!optionalProduct.isEmpty()) {
            Product pr = optionalProduct.get();
            if (pr != null) {
                pr.setNama(product.getNama());
                pr.setHarga(product.getHarga());
                pr.setKode(product.getKode());
                pr.setKategori(product.getKategori());
                return ResponseEntity.ok(productRepository.save(pr));
            }
        }
        return ResponseEntity.badRequest().body("Invalid Update");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProductById(
            @PathVariable(name = "id") Integer id) {
        Product product = productRepository.findById(id).get();
        if (product != null) {
            productRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.badRequest().body("Error");
    }
}

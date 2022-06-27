/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.backendkasir.kasir.repository;

import com.backendkasir.kasir.Entity.Product;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rahma
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{
    @Override
    @Cacheable(value = "getProduct")
    public Iterable<Product>findAll();
    
    @Override
    @Cacheable(value = "getProductById",key = "#id")
    public Optional<Product>findById(Integer id);
    
    @Cacheable(value = "getProductByIdkategori", key = "#idkategori")
    public Optional<Product>findByIdkategori(Integer idkategori);
    
    public Product findByid(Integer id);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.backendkasir.kasir.repository;

import com.backendkasir.kasir.Entity.Keranjangs;
import com.backendkasir.kasir.Entity.Product;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author rahma
 */
public interface KeranjangRepository extends CrudRepository<Keranjangs, Integer> {

    @Override
    @Cacheable(value = "getKeranjangs")
    public Iterable<Keranjangs> findAll();

    @Override
    @Cacheable(value = "getKeranjangById", key = "#id")
    public Optional<Keranjangs> findById(Integer id);

    public Keranjangs findByid(Integer id);
    
//    @Cacheable(value = "getKeranjangsByIdProduct",key = "#code")
//    public Optional<Keranjangs>findByIdProduct(String code);
//    
}

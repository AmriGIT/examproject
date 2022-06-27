/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backendkasir.kasir.repository;

import com.backendkasir.kasir.Entity.Kategori;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author LENOVO
 */
public interface KategoriRepository extends CrudRepository<Kategori, Integer>{
    @Override
    @Cacheable(value = "getKategori")
    public Iterable<Kategori> findAll();
    
    @Override
    @Cacheable(value= "getKategoriById", key = "#id")
    public Optional<Kategori> findById(Integer id);
    
    public Kategori findByid(Integer id);
}

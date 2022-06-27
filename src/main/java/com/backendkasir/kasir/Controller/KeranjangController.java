/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backendkasir.kasir.Controller;

import com.backendkasir.kasir.Entity.Kategori;
import com.backendkasir.kasir.Entity.Keranjangs;
import com.backendkasir.kasir.Entity.Product;
import com.backendkasir.kasir.repository.KeranjangRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rahma
 */
@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class KeranjangController {

    @Autowired
    private KeranjangRepository keranjangRepository;

    @GetMapping("/keranjangs")
    public ResponseEntity<List<Keranjangs>> getKeranjang() {
        Iterable<Keranjangs> keranjangs = keranjangRepository.findAll();
        List<Keranjangs> keranjangList = new ArrayList<>();
        for (Keranjangs keranjang : keranjangs) {
            keranjangList.add(keranjang);
        }
        return ResponseEntity.ok(keranjangList);
    }

    @DeleteMapping("/keranjangs/{id}")
    public ResponseEntity<String> deleteKeranjangById(@PathVariable(name = "id") Integer id) {
        Keranjangs keranjang = keranjangRepository.findById(id).get();
        if (keranjang != null) {
            keranjangRepository.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.badRequest().body("Error");
    }

//    @GetMapping("/keranjangs/product/{code}")
//    public ResponseEntity<Keranjangs> getKeranjangsByIdProduct(
//            @PathVariable(name = "code") String code) {
//        Optional<Keranjangs>optionalKeranjangs=keranjangRepository.findByIdProduct(code);
//        if(!optionalKeranjangs.isEmpty()){
//        return ResponseEntity.ok(optionalKeranjangs.get());
//        }
//        return ResponseEntity.badRequest().body(null);
//    }
    @PostMapping("/keranjangs/")
    public ResponseEntity<String> saveKeranjangs(@RequestBody Keranjangs keranjangs) {
        keranjangRepository.save(keranjangs);
        return ResponseEntity.ok("Success");
    }

}

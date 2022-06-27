
package com.backendkasir.kasir.Controller;

import com.backendkasir.kasir.Entity.Kategori;
import com.backendkasir.kasir.repository.UserRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendkasir.kasir.repository.KategoriRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class KategoriController {

    @Autowired
    private UserRepository userResRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    @GetMapping("/kategori")
    public ResponseEntity<List<Kategori>> getDisposisi() {
        Iterable<Kategori> kategoris = kategoriRepository.findAll();
        List<Kategori> kategoriList = new ArrayList<>();
        for (Kategori kategori : kategoris) {
            kategoriList.add(kategori);
        }
        return ResponseEntity.ok(kategoriList);
    }

    @PostMapping("/kategori/")
    public ResponseEntity<String> saveDisposisi(@RequestBody Kategori kategori) {
        kategoriRepository.save(kategori);
        return ResponseEntity.ok("Success");
    }

    @PutMapping("/kategori/")
    public ResponseEntity<?> updateKategori(@RequestBody Kategori kategori) {
        Optional<Kategori> optionalKategori = kategoriRepository.findById(kategori.getId());
        if (!optionalKategori.isEmpty()) {
            Kategori kat = optionalKategori.get();
            if (kat != null) {
                kat.setKategori(kategori.getKategori());
                return ResponseEntity.ok(kategoriRepository.save(kat));
            }
        }
        return ResponseEntity.badRequest().body("Invalid Update");
    }

    @DeleteMapping("/kategori/{id}")
    public ResponseEntity<String> deleteKategoriById(
            @PathVariable(name = "id") Integer id) {
        Kategori kategori = kategoriRepository.findById(id).get();
        if (kategori != null) {
            kategoriRepository.deleteById(id);
            return ResponseEntity.ok("Succes Deleted "+ kategori.getKategori());
        }
        return ResponseEntity.badRequest().body("Error");
    }
    

}

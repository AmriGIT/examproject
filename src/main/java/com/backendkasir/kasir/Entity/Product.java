
package com.backendkasir.kasir.Entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "t_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "kode", length = 255, nullable = false)
    private String kode;

    @Column(name = "nama", length = 255, nullable = false)
    private String nama;

    @Column(name = "harga", length = 255, nullable = false)
    private Integer harga;
    
    @ManyToOne
    @JoinColumn(name = "idkategori", nullable = false)
    private Kategori idkategori;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Kategori getKategori() {
        return idkategori;
    }

    public void setKategori(Kategori idkategori) {
        this.idkategori = idkategori;
    }


}

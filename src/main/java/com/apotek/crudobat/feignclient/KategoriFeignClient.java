package com.apotek.crudobat.feignclient;

import com.apotek.crudobat.model.KategoriDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name = "kategori") // iki isi en sesuai nama app microservice nak eureka e
public interface KategoriFeignClient {
    @GetMapping("/kategori/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    List<KategoriDTO> getAllKategori();

    @GetMapping("/kategori/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    KategoriDTO getKategoriById(@PathVariable Long id);
}

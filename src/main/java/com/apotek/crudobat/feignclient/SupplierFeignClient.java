package com.apotek.crudobat.feignclient;
import com.apotek.crudobat.model.SupplierDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FeignClient(name = "supplier") // iki isi en sesuai nama app microservice nak eureka e
public interface SupplierFeignClient {
    @GetMapping("/supplier/all") //
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    List<SupplierDTO> getAllSupplier();

    @GetMapping("/supplier/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    SupplierDTO getSupplierById(@PathVariable Long id);
}

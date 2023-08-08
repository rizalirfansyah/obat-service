package com.apotek.crudobat.controller;

import com.apotek.crudobat.feignclient.KategoriFeignClient;
import com.apotek.crudobat.feignclient.SupplierFeignClient;
import com.apotek.crudobat.model.KategoriDTO;
import com.apotek.crudobat.model.ObatDTO;
//import com.apotek.crudobat.model.TransactionDTO;
import com.apotek.crudobat.model.ObatHistoryDTO;
import com.apotek.crudobat.repository.ObatRepository;
import com.apotek.crudobat.service.ObatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obat")
public class ObatController {
    private final ObatService obatService;
    private final ObatRepository obatRepository;

    private final SupplierFeignClient supplierFeignClient;

    private final KategoriFeignClient kategoriFeignClient;

    private final ModelMapper modelMapper;

    @Autowired
    public ObatController(ObatService obatService, ObatRepository obatRepository, SupplierFeignClient supplierFeignClient, KategoriFeignClient kategoriFeignClient, ModelMapper modelMapper) {
        this.obatService = obatService;
        this.obatRepository = obatRepository;
        this.supplierFeignClient = supplierFeignClient;
        this.kategoriFeignClient = kategoriFeignClient;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/kategori/all")
    public List<KategoriDTO> getAllKategori(){
        List<KategoriDTO> kategori = kategoriFeignClient.getAllKategori();
        return kategori;
    }

    @GetMapping("/kategori/{id}")
    public KategoriDTO getKategoriById(@PathVariable Long id) {
        KategoriDTO kategori = kategoriFeignClient.getKategoriById(id);
        return kategori;
    }

    @GetMapping("/history/all")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    public List<ObatHistoryDTO> getAllObatsHistory(){
        return obatService.getAllObatsHistory();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    public List<ObatDTO> getAllObats() {
        return obatService.getAllObats();
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ObatDTO createObat(@RequestBody ObatDTO obatDTO) {
        return obatService.createObat(obatDTO);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ObatDTO updateObat(@PathVariable Long id, @RequestBody ObatDTO obatDTO) {
        return obatService.updateObat(id, obatDTO);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteObat(@PathVariable Long id) {
        obatService.deleteObat(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('CASHIER')")
    public ObatDTO getObatById(@PathVariable Long id) {
        return obatService.getObatById(id);
    }

//
//    @GetMapping("/history/{id}")
//    public ObatHistory getObatHistoryById(@PathVariable Long id) {
//        return obatService.getObatHistoryById(id);
//    }

}

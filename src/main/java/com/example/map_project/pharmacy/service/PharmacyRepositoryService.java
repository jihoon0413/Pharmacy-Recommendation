package com.example.map_project.pharmacy.service;

import com.example.map_project.pharmacy.entity.Pharmacy;
import com.example.map_project.pharmacy.repository.PharmacyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRepositoryService {

    private final PharmacyRepository pharmacyRepository;

    @Transactional
    public void updateAddress(Long id, String address) {

        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if(Objects.isNull(pharmacy)) {
            log.error("[PharmacyRepositoryService updateAddress] not found id: {}", id);
            return;
        }

        pharmacy.changePharmacyAddress(address);


    }

    public void updateAddressWithoutTransaction(Long id, String address) {

        Pharmacy pharmacy = pharmacyRepository.findById(id).orElse(null);

        if(Objects.isNull(pharmacy)) {
            log.error("[PharmacyRepositoryService updateAddress] not found id: {}", id);
            return;
        }

        pharmacy.changePharmacyAddress(address);


    }



}

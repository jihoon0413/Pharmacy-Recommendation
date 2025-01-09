package com.example.map_project.pharmacy.service;

import com.example.map_project.AbstractIntegrationContainerBaseTest
import com.example.map_project.pharmacy.entity.Pharmacy;
import com.example.map_project.pharmacy.repository.PharmacyRepository;
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime;

public class PharmacyRepositoryServiceTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepositoryService pharmacyRepositoryService;

    @Autowired
    private PharmacyRepository pharmacyRepository;

    def setup() {
        pharmacyRepository.deleteAll()
    }

    def "PharmacyRepository update - dirty checking success"() {

        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy);
        pharmacyRepositoryService.updateAddress(entity.getId(), modifiedAddress);

        def result = pharmacyRepository.findAll()

        then:
        result.get(0).getPharmacyAddress() == modifiedAddress

    }

    def "PharmacyRepository update - dirty checking fail"() {

        given:
        String inputAddress = "서울 특별시 성북구 종암동"
        String modifiedAddress = "서울 광진구 구의동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        def entity = pharmacyRepository.save(pharmacy);
        pharmacyRepositoryService.updateAddressWithoutTransaction(entity.getId(), modifiedAddress);

        def result = pharmacyRepository.findAll()

        then:
        result.get(0).getPharmacyAddress() == inputAddress

    }


    def "BaseTimeEntity등록"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        String inputAddress = "서울 특별시 성북구 종암동"
        String name = "은혜 약국"

        def pharmacy = Pharmacy.builder()
                .pharmacyAddress(inputAddress)
                .pharmacyName(name)
                .build()

        when:
        pharmacyRepository.save(pharmacy)
        def result = pharmacyRepository.findAll()

        then:
        result.get(0).getCreatedDate().isAfter(now)
        result.get(0).getModifiedDate().isAfter(now)

    }


}

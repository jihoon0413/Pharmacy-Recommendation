package com.example.map_project.pharmacy.repository

import com.example.map_project.AbstractIntegrationContainerBaseTest
import com.example.map_project.pharmacy.entity.Pharmacy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PharmacyRepositoryTest extends AbstractIntegrationContainerBaseTest {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    def "PharmacyRepository save"() {

        given:
        def pharmacy = Pharmacy.builder()
                .pharmacyAddress("서울 특별시 성북구 종암동")
                .pharmacyName("은헤 약국")
                .latitude(36.11)
                .longitude(128.11)
                .build()

        when:
        def result = pharmacyRepository.save(pharmacy);

        then:
        result.getPharmacyAddress() == "서울 특별시 성북구 종암동"
    }

    def "PharmacyRepository saveAll"() {

        given:
        def pharmacy = Pharmacy.builder()
                .pharmacyAddress("서울 특별시 성북구 종암동")
                .pharmacyName("은헤 약국")
                .latitude(36.11)
                .longitude(128.11)
                .build()

        when:
        def result = pharmacyRepository.saveAll(Arrays.asList(pharmacy));

        then:
        result.size() == 1
    }
}

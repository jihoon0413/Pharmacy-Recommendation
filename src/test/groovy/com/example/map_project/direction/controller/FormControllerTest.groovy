package com.example.map_project.direction.controller

import com.example.map_project.direction.dto.InputDto
import com.example.map_project.direction.dto.OutputDto
import com.example.map_project.pharmacy.service.PharmacyRecommendationService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*

class FormControllerTest extends Specification{

    private MockMvc mockMvc
    private PharmacyRecommendationService pharmacyRecommendationService = Mock()
    private List<OutputDto> outputDtoList

    def setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new FormController(pharmacyRecommendationService))
        .build()

        outputDtoList = new ArrayList<>()
        outputDtoList.addAll(
                OutputDto.builder()
                        .pharmacyName("pharmacy1")
                        .build(),
                OutputDto.builder()
                        .pharmacyName("pharmacy2")
                        .build()
        )
    }

    def "GET /"() {
        expect:
        mockMvc.perform (get("/"))
                .andExpect (handler().handlerType(FormController.class))
                .andExpect(handler().methodName("main"))
                .andExpect(status().isOk())
                .andExpect(view().name("main"))
                .andDo (log())
    }

    def "POST /search"() {
        given:
        InputDto inputDto = new InputDto()
        inputDto.setAddress("서울 성북구")

        when:
        pharmacyRecommendationService.recommendPharmacyList("서울 성북구") >> outputDtoList
        ResultActions result = mockMvc.perform(
                post("/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputDto.toString()))

        then:
        result.andExpect(status().isOk())
                .andExpect(view().name("output"))
                .andExpect(model().attributeExists("outputFormList"))
                .andExpect(model().attribute("outputFormList", outputDtoList))
                .andDo(print())

    }


}

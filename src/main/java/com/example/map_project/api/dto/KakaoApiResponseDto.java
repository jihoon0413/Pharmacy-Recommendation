package com.example.map_project.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class KakaoApiResponseDto {

    @JsonProperty("meta")
    private MetaDto metaDto;

    @JsonProperty("documents")
    private List<DocumentDto> documentList;


}

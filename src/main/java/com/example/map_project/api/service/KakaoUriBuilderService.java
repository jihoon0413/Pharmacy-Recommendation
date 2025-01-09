package com.example.map_project.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service    
public class KakaoUriBuilderService {

    private static final String KAKAO_LOCAL_SEARCH_ADDRESS_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    private static final String KAKAO_LOCAL_CATEGORY_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/category.json";

    public URI buildUriByAddressSearch(String address) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUri(URI.create(KAKAO_LOCAL_SEARCH_ADDRESS_URL));
        uriBuilder.queryParam("query",address);

        URI uri = uriBuilder.build().encode().toUri();
        log.info("[KakaoUriBuilderService builderUriByAddressSearch] address: {}, uri: {}", address, uri);

        return uri;
    }
}

package com.spotify4party.integration.service;

import com.spotify4party.config.SpotifyAuthorizationConfig;
import com.spotify4party.integration.dto.authenticate.SpotifyAuthtResponseDTO;
import com.spotify4party.integration.handler.ResponseHandler;
import com.yfwould.rest.UnderratedRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;

@Service
public class SpotifyAuthorizationIntegrationService {

    @Autowired
    private SpotifyAuthorizationConfig spotifyConfig;

    @Autowired
    private ResponseHandler responseHandler;

    public SpotifyAuthtResponseDTO authenticate(){
        ResponseEntity<SpotifyAuthtResponseDTO> response = UnderratedRestClient.build()
                .addInterceptor(new BasicAuthorizationInterceptor(spotifyConfig.getClientID(), spotifyConfig.getClientSecret()))
                .addMessageConverter(new FormHttpMessageConverter())
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED.toString())
                .baseUrl(spotifyConfig.getAuthorizationAPI())
                .resource(spotifyConfig.getAuthenticateService())
                .method(HttpMethod.POST)
                .payload(new LinkedMultiValueMap<String,String>(){{
                    add(spotifyConfig.getGrantTypeKey(), spotifyConfig.getGrantTypeValue());
                    add(spotifyConfig.getScopeKey(), spotifyConfig.getScopeValue());
                }})
                .execute(SpotifyAuthtResponseDTO.class);

        return responseHandler.handleResponse(response);
    }
}
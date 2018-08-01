package com.goodweather.integration.service;

import com.goodweather.config.OpenWeaterConfig;
import com.goodweather.integration.dto.weather.OpenWeatherDTO;
import com.yfwould.rest.UnderratedRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenWeatherIntegrationService {

    @Autowired
    private OpenWeaterConfig openWeaterConfig;

    public OpenWeatherDTO findWeather(Map<String, String> params){
        ResponseEntity<OpenWeatherDTO> response = UnderratedRestClient.build()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString())
                .baseUrl(openWeaterConfig.getUrl())
                .resource(openWeaterConfig.getWeatherService())
                .method(HttpMethod.GET)
                .addParam(openWeaterConfig.getApikeyKey(), openWeaterConfig.getApikeyValue())
                .addParam(openWeaterConfig.getTemperatureUnityKey(), openWeaterConfig.getTemperatureUnityValue())
                .addExtraParams(params)
                .execute(OpenWeatherDTO.class);

        return response.getBody();
    }
}
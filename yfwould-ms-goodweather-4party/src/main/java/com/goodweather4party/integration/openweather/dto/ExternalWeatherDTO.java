package com.goodweather4party.integration.openweather.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalWeatherDTO implements Serializable {

    public Integer temperature;

}
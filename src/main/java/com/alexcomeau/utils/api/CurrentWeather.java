package com.alexcomeau.utils.api;

import com.alexcomeau.response.currentweather.Response;
import com.alexcomeau.utils.ReadToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CurrentWeather {
    //format the input into a city request
    public static String currentWeatherRequest(String city) {
        //the template for the request
        final String template = "http://api.openweathermap.org/data/2.5/weather?q=$&appid=";

        //replace the dollar sign with the city and so on
        String out = template.replace("$", city) + ReadToken.WeatherToken();

        //return the formatted request
        return out;
    }

    //map the raw json to a Weather object
    public static Response currentWeatherAsObject(String json) throws JsonProcessingException {
        //create an object mapper
        ObjectMapper om = new ObjectMapper();

        //map the json
        Response r = om.readValue(json, Response.class);

        //return the json
        return r;
    }
}

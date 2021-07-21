package com.alexcomeau.utils.api;

import com.alexcomeau.response.googleGeocoding.GoogleGeocodingStruct;
import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.ReadToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GoogleGeocode {

    public static String googleGeocodingRequest(String city){
        final String template = "https://maps.googleapis.com/maps/api/geocode/json?address=$&key=";
        city = ApiRequest.cleanRequest(city).trim();
        city = city.replace(" ", "$").replace("$", "%20");
        return template.replace("$", city) + ReadToken.GeoCodingToken();
    }

    public static GoogleGeocodingStruct jsonToObject(String json) throws JsonProcessingException {
        //create an object mapper
        ObjectMapper om = new ObjectMapper();

        //map the json
        GoogleGeocodingStruct r = om.readValue(json, GoogleGeocodingStruct.class);

        //return the json
        return r;
    }
}

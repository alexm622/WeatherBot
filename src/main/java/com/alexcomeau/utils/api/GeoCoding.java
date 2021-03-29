package com.alexcomeau.utils.api;

import com.alexcomeau.response.currentweather.Response;
import com.alexcomeau.response.geocoding.GeoCodingStruct;
import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.ReadToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeoCoding {

    public static String geoCodingRequest(String city){
        final String template = "https://maps.googleapis.com/maps/api/geocode/json?address=$&key=";
        city = ApiRequest.cleanRequest(city);
        city = city.replace(" ", "$").replace("$", "%20");
        return template.replace("$", city) + ReadToken.GeoCodingToken();
    }

    public static GeoCodingStruct jsonToObject(String json) throws JsonProcessingException {
        //create an object mapper
        ObjectMapper om = new ObjectMapper();

        //map the json
        GeoCodingStruct r = om.readValue(json, GeoCodingStruct.class);

        //return the json
        return r;
    }
}

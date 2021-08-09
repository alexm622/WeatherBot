package com.alexcomeau.utils.api;

import com.alexcomeau.response.googleGeocoding.GoogleGeocodingStruct;
import com.alexcomeau.response.tomtomGeocoding.TomTomGeocodingStruct;
import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.ReadToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TomTomGeocode {
    public static String tomtomGeocodingRequest(String city){
        city = ApiRequest.cleanRequest(city);
        String url = "https://api.tomtom.com/search/2/geocode/*.json&key=" + ReadToken.TomTomToken();
        url = url.replace("*", city);
        return url;
    }

    public static TomTomGeocodingStruct jsonToObject(String json) throws JsonProcessingException {
        //create an object mapper
        ObjectMapper om = new ObjectMapper();

        //map the json
        TomTomGeocodingStruct r = om.readValue(json, TomTomGeocodingStruct.class);

        //return the json
        return r;
    }
    
}

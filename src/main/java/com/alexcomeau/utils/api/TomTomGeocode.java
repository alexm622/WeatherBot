package com.alexcomeau.utils.api;

import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.ReadToken;

public class TomTomGeocode {
    public static String tomtomGeocodingRequest(String city){
        city = ApiRequest.cleanRequest(city);
        String url = "https://api.tomtom.com/search/2/geocode/json?key=" + ReadToken.TomTomToken() + "&q=" + city;
        return ApiRequest.get(url);
    }
    
}

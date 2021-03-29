package com.alexcomeau.utils;

import com.alexcomeau.response.currentweather.Response;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.utils.ReadToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiRequest {
    //make an api request
    public static String makeRequest(String apiRequest) throws IOException {
        //execute the api request
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(apiRequest);
        HttpResponse response = client.execute(request);
        //create a buffered reader for reading the response
        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));
        //create a stringbuilder to make the api response into a single string
        StringBuilder apiResponse = new StringBuilder();
        String temp;
        //loop throught the api response
        while ((temp = rd.readLine()) != null) {
            //if line of text exists add it to the output string
            apiResponse.append(temp);
        }
        //debug print the api response
        Debug.debug("api response was: \n" + apiResponse.toString());
        return apiResponse.toString().replace("1h", "one").replace("3h", "three");

    }

    public static String cleanRequest(String city){
        //check for invalid characters
        if(hasSpecialCharacters(city)){
            //if it has special characters return invalid
            return "invalid";
        }
        //return the un-altered version if no special characters
        return city;
    }

    public static boolean hasSpecialCharacters(String s) {
        //check for empty string
        if (s == null || s.trim().isEmpty()) {
            //if the string is empty return true
            return true;
        }
        //create a pattern that checks for characters which are not alphanumeric and are not a dot, dash, or comma
        Pattern p = Pattern.compile("[^A-Za-z0-9. ,]^-");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b){
            //return true, there is special characters
            return true;
        } else {
            //return false, there is no special characters
            return false;
        }
    }

}

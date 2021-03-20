package com.alexcomeau;

import com.alexcomeau.response.Weather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public static String makeRequest(String apiRequest) throws IOException {
        String apiReq= apiRequest;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(apiReq);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader
                (new InputStreamReader(
                        response.getEntity().getContent()));

        StringBuilder apiResponse = new StringBuilder();
        String temp;
        while ((temp = rd.readLine()) != null) {
            apiResponse.append(temp);
        }
        return apiResponse.toString();

    }

    public static String cleanRequest(String city){
        if(hasSpecialCharacters(city)){
            return "invalid";
        }

        return city;
    }

    public static boolean hasSpecialCharacters(String s) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return true;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9. - ,]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b){
            System.out.println("There is a special character in my string ");
            return true;
        } else {
            System.out.println("There is no special char.");
            return false;
        }
    }

    public static String createRequest(String city) {
        final String template = "http://api.openweathermap.org/data/2.5/weather?q=$&appid=";
        String out = template.replace("$", city) + ReadToken.WeatherToken();
        return out;
    }

    public static Weather jsonToObject(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        Weather w = om.readValue(json, Weather.class);
        return w;
    }
}

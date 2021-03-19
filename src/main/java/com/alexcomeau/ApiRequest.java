package com.alexcomeau;

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
        String apiReq= "";
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

    public static String formatRequest(String city){
        if(getSpecialCharacters(city)){
            return "invalid";
        }

        return null;
    }

    public static boolean getSpecialCharacters(String s) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return true;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9. - ]");
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
}

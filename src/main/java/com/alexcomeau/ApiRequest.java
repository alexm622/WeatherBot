package com.alexcomeau;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApiRequest {
    public static String makeRequest() throws IOException {
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
}

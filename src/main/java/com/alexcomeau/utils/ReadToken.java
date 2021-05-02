package com.alexcomeau.utils;

import com.alexcomeau.sql.LoginDetails;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadToken {
    public static String DiscordToken(){
        String tokenPath = "/tokens/weatherbot.txt";
        String token;
        try{
            BufferedReader br = new BufferedReader(new FileReader(tokenPath));
            token = br.readLine();
        } catch (IOException e) {
            Debug.debug(e.toString(), true);
            token = "0";
            return token;
        }
        return token;
    }

    public static String WeatherToken(){
        String tokenPath = "/tokens/openweather.txt";
        String token;
        try{
            BufferedReader br = new BufferedReader(new FileReader(tokenPath));
            token = br.readLine();
        } catch (IOException e) {
            Debug.debug(e.toString(), true);
            token = "0";
            return token;
        }
        return token;
    }

    public static String GeoCodingToken(){
        String tokenPath = "/tokens/geocoding.txt";
        String token;
        try{
            BufferedReader br = new BufferedReader(new FileReader(tokenPath));
            token = br.readLine();
        } catch (IOException e) {
            Debug.debug(e.toString(), true);
            token = "0";
            return token;
        }
        return token;
    }

    public static LoginDetails getSql(){
        String tokenPath = "/tokens/sql.txt";
        String temp;
        LoginDetails logDets = new LoginDetails();
        try{
            BufferedReader br = new BufferedReader(new FileReader(tokenPath));
            temp = br.readLine();
            String[] fields = temp.split("/");
            logDets.setIp(fields[0]);
            logDets.setUsername(fields[1]);
            logDets.setPassword(fields[2]);
        } catch (Exception e) {
            logDets.setIp("127.0.0.1");
            logDets.setUsername("none");
            logDets.setPassword("none");
        }
        return logDets;
    }


}

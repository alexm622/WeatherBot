package com.alexcomeau.utils;

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
        } catch (FileNotFoundException e) {
            Debug.debug(e.toString(), true);
            token = "0";
            return token;
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
        } catch (FileNotFoundException e) {
            Debug.debug(e.toString(), true);
            token = "0";
            return token;
        } catch (IOException e) {
            Debug.debug(e.toString(), true);
            token = "0";
            return token;
        }
        return token;
    }


}

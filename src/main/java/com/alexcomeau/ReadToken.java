package com.alexcomeau;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadToken {
    public static String ReadToken(){
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

}

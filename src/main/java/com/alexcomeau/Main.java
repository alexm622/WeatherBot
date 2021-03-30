package com.alexcomeau;

import com.alexcomeau.bot.Bot;
import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.utils.ReadToken;
import com.alexcomeau.utils.api.GeoCoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;


public class Main {
    public static final String prefix = "&"; // TODO this will later be changeable by the user

    public static void main(String[] args) throws LoginException {

        JDABuilder.createLight(ReadToken.DiscordToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("Poop Fart"))
                .addEventListeners(new Bot())
                .build();
        /*
        String request = "Plainville MA";
        request = GeoCoding.geoCodingRequest(request);
        Debug.debug("request is: " + request);
        String json = "{\"status\":\"failed\"}";
        try {
            json = ApiRequest.makeRequest(request);
        }catch(Exception e){
            Debug.debug(e.getClass().toString(), true);
        }
        Debug.debug("the json is: " + json);
        try {
            GeoCoding.jsonToObject(json);
        }catch(Exception e){
            e.printStackTrace();
        }*/


    }
}

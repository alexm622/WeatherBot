package com.alexcomeau;

import com.alexcomeau.response.Response;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.security.auth.login.LoginException;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws LoginException, IOException {
        /*JDABuilder.createLight(ReadToken.DiscordToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("Type &ping"))
                .addEventListeners(new Bot())
                .build();*/
        String response = ApiRequest.makeRequest();
        Debug.debug(response);
        ObjectMapper om = new ObjectMapper();
        Response r = om.readValue(response, Response.class);
    }
}

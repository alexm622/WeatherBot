package com.alexcomeau;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;



public class Main {


    public static void main(String[] args) throws LoginException {
        JDABuilder.createLight(ReadToken.DiscordToken(), GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("Type &ping"))
                .addEventListeners(new Bot())
                .build();
    }
}

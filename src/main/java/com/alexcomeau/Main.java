package com.alexcomeau;

import com.alexcomeau.bot.Bot;
import com.alexcomeau.utils.ReadToken;
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

    }
}

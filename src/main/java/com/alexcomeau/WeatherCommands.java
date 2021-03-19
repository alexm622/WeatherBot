package com.alexcomeau;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class WeatherCommands {
    public static void weatherCommands(MessageReceivedEvent event, Message msg, String prefix){
        if(msg.getContentRaw().startsWith(prefix + "w ")){

            String[] subs = msg.getContentRaw().split(" ");
            String city = subs[1];



        }
    }
}

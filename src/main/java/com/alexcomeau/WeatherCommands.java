package com.alexcomeau;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class WeatherCommands {
    public static boolean weatherCommands(MessageReceivedEvent event, Message msg, String prefix) throws IOException {
        if(msg.getContentRaw().startsWith(prefix + "w ")){

            String[] subs = msg.getContentRaw().split(" ");
            StringBuilder sb = new StringBuilder();
            int temp = 0;
            for(String s : subs) {
                if (temp == 0) {
                    temp++;
                    continue;
                }
                sb.append(s + " ");
            }

            String city = sb.toString().trim();

            city=ApiRequest.cleanRequest(city);

            String request = ApiRequest.createRequest(city);
            String output = ApiRequest.makeRequest(request);
            MessageChannel channel = event.getChannel();
            channel.sendMessage(output) /* => RestAction<Message> */
                    .queue();
            //do stuff
            return true;




        }
        return false;
    }

}

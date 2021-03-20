package com.alexcomeau;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class WeatherCommands {
    public static boolean weatherCommands(MessageReceivedEvent event, Message msg, String prefix) throws IOException {
        //Debug.debug("does message start with &w: " + msg.getContentRaw().startsWith(prefix + "w "));
        if(msg.getContentRaw().startsWith(prefix + "w")){

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
           // Debug.debug("got city of " + city);

            city=ApiRequest.cleanRequest(city);
            //Debug.debug("cleaned " + city);

            String request = ApiRequest.createRequest(city).replace(" ", "$").replace("$", "%20");
            //Debug.debug("got request request of " + request);
            String output = ApiRequest.makeRequest(request);
            //Debug.debug("got request output of " + request);
            MessageChannel channel = event.getChannel();
            channel.sendMessage(output) /* => RestAction<Message> */
                    .queue();
            //do stuff
            return true;




        }
        return false;
    }

}

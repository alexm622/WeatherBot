package com.alexcomeau;

import com.alexcomeau.response.Response;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class WeatherCommands {
    public static boolean weatherCommands(MessageReceivedEvent event, Message msg, String prefix) throws IOException {
        //Debug.debug("does message start with &w: " + msg.getContentRaw().startsWith(prefix + "w "));
        if(msg.getContentRaw().startsWith(prefix + "w")){

            //split the message at the spaces
            String[] subs = msg.getContentRaw().split(" ");

            //create the necessary stuff for building the final command
            StringBuilder sb = new StringBuilder();
            int temp = 0;

            //start creating the final city name
            for(String s : subs) {
                //ignore the first substring (this is the command)
                if (temp == 0) {
                    temp++;
                    continue;
                }
                //append all following substrings to the final string
                sb.append(s).append(" ");
            }

            //get the final string from the StringBuilder and remove trailing blank spaces
            String city = sb.toString().trim();

           // Debug.debug("got city of " + city);

            //clean the request, if it contains special characters we remove it
            city=ApiRequest.cleanRequest(city);

            //Debug.debug("cleaned " + city);

            //create the request while replaces blank spaces with "%20"
            String request = ApiRequest.createRequest(city).replace(" ", "$").replace("$", "%20");
            //Debug.debug("got request request of " + request);

            //make the api request
            String output = ApiRequest.makeRequest(request);

            //Debug.debug("got request output of " + request);

            //get the channel
            MessageChannel channel = event.getChannel();

            //currently this will send the raw json response
            //TODO make this print out an embedded and handle error messages
            channel.sendMessage(output) /* => RestAction<Message> */
                    .queue();
            Response r = ApiRequest.jsonToObject(output);
            channel.sendMessage(WeatherEmbed.buildEmbeded(r, city)).queue();
            return true;




        }
        return false;
    }

}

package com.alexcomeau.bot.commands.weather;

import com.alexcomeau.bot.commands.CommandStruct;
import com.alexcomeau.bot.commands.CommandType;
import com.alexcomeau.bot.embeds.CurrentWeatherEmbed;
import com.alexcomeau.response.currentweather.Response;
import com.alexcomeau.response.geocoding.GeoCodingStruct;
import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.CommandParser;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.utils.api.CurrentWeather;
import com.alexcomeau.utils.api.GeoCoding;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class WeatherCommands {
    public static boolean dailyWeather(MessageReceivedEvent event, Message msg, WeatherCommandStruct cs) throws IOException {
        //Debug.debug("does message start with &w: " + msg.getContentRaw().startsWith(prefix + "w "));
        String alex = "227478475760599041";
        if(msg.getAuthor().getId().equals(alex)){

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

            GeoCodingStruct geoLocate;

            String geoRequest = GeoCoding.geoCodingRequest(city);
            String json = "{\"status\":\"failed\"}";
            try {
                json = ApiRequest.makeRequest(geoRequest);
            }catch(Exception e){
                Debug.debug(e.getClass().toString(), true);
            }
            Debug.debug("the json is: " + json);
            geoLocate = GeoCoding.jsonToObject(json);

            //create the request while replaces blank spaces with "%20"
            String currentRequest = CurrentWeather.currentWeatherRequest(geoLocate);


            //make the api request
            String output = ApiRequest.makeRequest(currentRequest);



            //get the channel
            MessageChannel channel = event.getChannel();

            //currently this will send the raw json response
            //TODO make this print out an embedded and handle error messages
            channel.sendMessage(output) /* => RestAction<Message> */
                    .queue();
            Response r = CurrentWeather.currentWeatherAsObject(output);
            channel.sendMessage(CurrentWeatherEmbed.buildEmbeded(r, geoLocate)).queue();
            return true;




        }
        return false;
    }


    public static boolean weatherCommands(MessageReceivedEvent event, Message msg, String prefix) throws IOException {
        String message = msg.getContentRaw();
        CommandStruct cs = CommandParser.parseCommand(message);
        if(!(cs.command == CommandType.WEATHER)){
            return false;
        }
        WeatherArg whatType = WeatherArg.WEEKLY, whatUnit = WeatherArg.IMPERIAL;
        WeatherCommandStruct w = (new WeatherCommandStruct()).fromCommandStruct(cs);

        for(WeatherArg wArg : w.args){
            if(wArg.getType() == WeatherArgType.UNITS){
                whatUnit = wArg;
            }else if(wArg.getType() == WeatherArgType.SUBCOMMAND){
                whatType = wArg;
            }
        }

        switch(whatType){
            case DAILY:
                dailyWeather(event, msg, w);
                break;
            default:
                break;
        }

        return true;
    }


}
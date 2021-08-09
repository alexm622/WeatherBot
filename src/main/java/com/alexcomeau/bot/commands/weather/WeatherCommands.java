package com.alexcomeau.bot.commands.weather;

import com.alexcomeau.bot.commands.CommandStruct;
import com.alexcomeau.bot.commands.CommandType;
import com.alexcomeau.bot.embeds.CurrentWeatherEmbed;
import com.alexcomeau.bot.embeds.DailyWeatherEmbed;
import com.alexcomeau.response.currentweather.CurrentWeatherResponse;
import com.alexcomeau.response.googleGeocoding.GoogleGeocodingStruct;
import com.alexcomeau.response.tomtomGeocoding.*;
import com.alexcomeau.response.weekforecast.Weekly;
import com.alexcomeau.utils.ApiRequest;
import com.alexcomeau.utils.CommandParser;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.utils.api.CurrentWeather;
import com.alexcomeau.utils.api.DailyWeather;
import com.alexcomeau.utils.api.GoogleGeocode;
import com.alexcomeau.utils.api.TomTomGeocode;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public class WeatherCommands {
    public static boolean dailyWeather(MessageReceivedEvent event, Message msg, WeatherCommandStruct cs, WeatherArg whatUnit) throws IOException {
        //Debug.debug("does message start with &w: " + msg.getContentRaw().startsWith(prefix + "w "));
        String alex = "227478475760599041";
        if(msg.getAuthor().getId().equals(alex)){
            //split the message at the spaces
            String[] subs = msg.getContentRaw().split(" ");
            //create the necessary stuff for building the final command
            StringBuilder sb = new StringBuilder();
            int temp = 0;


            //get the final string from the StringBuilder and remove trailing blank spaces


           // Debug.debug("got city of " + city);

            //clean the request, if it contains special characters we remove it
            /* 
             * TODO: make this work with both api's
             * this should be able to differentiate between the two api's
             * and pick the best one
             */
            GoogleGeocodingStruct googleLocate;
            TomTomGeocodingStruct tomtomLocate;
            //get the channel
            MessageChannel channel = event.getChannel();

            String output;
            try{

                String geoRequest = GoogleGeocode.googleGeocodingRequest(cs.input);
                String json = "{\"status\":\"failed\"}";
                output = json;
                try {
                    json = ApiRequest.makeRequest(geoRequest);
                }catch(Exception e){
                    Debug.debug(e.getClass().toString(), true);
                }
                Debug.debug("the json is: " + json);
                output = json;
                googleLocate = GoogleGeocode.jsonToObject(json);
                Debug.debug("the status was " + googleLocate.getStatus());
                if(!googleLocate.getStatus().equals("OK")){
                    googleLocate = null;
                    Debug.debug("google failed");
                    //switch to using tomtom
                    String tomtomRequest = TomTomGeocode.tomtomGeocodingRequest(cs.input);
                    Debug.debug("the request is " + tomtomRequest);
                    json = "{\"status\":\"failed\"}";
                    try {
                        json = ApiRequest.makeRequest(tomtomRequest);
                    }catch(Exception e){
                        Debug.debug(e.getClass().toString(), true);
                    }
                    tomtomLocate = TomTomGeocode.jsonToObject(tomtomRequest);
                    try {
                        json = ApiRequest.makeRequest(tomtomRequest);

                    }catch(Exception e){
                        Debug.debug(e.getClass().toString(), true);
                    }
                    if(!tomtomLocate.getStatus().equals("OK")){
                        Debug.debug("tomtom failed");
                        //send a message to the channel
                        event.getChannel().sendMessage("I couldn't find that city, please try again.").queue();
                        return false;
                    }else{
                        //do stuff
                        //create the request while replaces blank spaces with "%20"
                        String currentRequest = CurrentWeather.currentWeatherRequest(tomtomLocate);

                        //make the api request
                        output = ApiRequest.makeRequest(currentRequest);

                        //TODO make this print out an embedded and handle error messages
                        channel.sendMessage(output) /* => RestAction<Message> */
                                .queue();
                        CurrentWeatherResponse r = CurrentWeather.currentWeatherAsObject(output);
                        channel.sendMessage(CurrentWeatherEmbed.buildEmbeded(r, googleLocate, whatUnit)).queue();
                    }
                }else{
                    //create the request while replaces blank spaces with "%20"
                    String currentRequest = CurrentWeather.currentWeatherRequest(googleLocate);


                    //make the api request
                    output = ApiRequest.makeRequest(currentRequest);
                    //TODO make this print out an embedded and handle error messages
                    channel.sendMessage(output) /* => RestAction<Message> */
                            .queue();
                    CurrentWeatherResponse r = CurrentWeather.currentWeatherAsObject(output);
                    channel.sendMessage(CurrentWeatherEmbed.buildEmbeded(r, googleLocate, whatUnit)).queue();
                }
            }catch(Exception e){
                e.printStackTrace();
                Debug.debug("both options failed");
            }

            return true;

        }
        return false;
    }

    public static boolean weeklyWeather(MessageReceivedEvent event, Message msg, WeatherCommandStruct cs, WeatherArg unit) throws IOException {
        //Debug.debug("does message start with &w: " + msg.getContentRaw().startsWith(prefix + "w "));
        String alex = "227478475760599041";
        if(msg.getAuthor().getId().equals(alex)){
            //split the message at the spaces
            String[] subs = msg.getContentRaw().split(" ");
            //create the necessary stuff for building the final command
            StringBuilder sb = new StringBuilder();
            int temp = 0;


            //get the final string from the StringBuilder and remove trailing blank spaces


            // Debug.debug("got city of " + city);

            //clean the request, if it contains special characters we remove it

            GoogleGeocodingStruct geoLocate;

            String geoRequest = GoogleGeocode.googleGeocodingRequest(cs.input);
            String json = "{\"status\":\"failed\"}";
            try {
                json = ApiRequest.makeRequest(geoRequest);
            }catch(Exception e){
                Debug.debug(e.getClass().toString(), true);
            }
            Debug.debug("the json is: " + json);
            geoLocate = GoogleGeocode.jsonToObject(json);

            //create the request while replaces blank spaces with "%20"
            String currentRequest = DailyWeather.dailyWeatherRequest(geoLocate);


            //make the api request
            String output = ApiRequest.makeRequest(currentRequest);



            //get the channel
            MessageChannel channel = event.getChannel();

            //currently this will send the raw json response
            //TODO make this print out an embedded and handle error messages
            //channel.sendMessage(output) /* => RestAction<Message> */
              //      .queue();
            Weekly r = DailyWeather.dailyWeatherAsObject(output);
            channel.sendMessage(DailyWeatherEmbed.buildEmbed(r, geoLocate, unit)).queue();
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
        WeatherArg whatType = WeatherArg.DAILY, whatUnit = WeatherArg.IMPERIAL;
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
                dailyWeather(event, msg, w, whatUnit);
                break;
            case WEEKLY:
                weeklyWeather(event, msg, w, whatUnit);
                break;
            default:
                break;
        }

        return true;
    }


}

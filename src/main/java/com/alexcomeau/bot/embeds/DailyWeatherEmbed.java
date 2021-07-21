package com.alexcomeau.bot.embeds;

import com.alexcomeau.bot.commands.weather.WeatherArg;
import com.alexcomeau.response.googleGeocoding.AddressComponent;
import com.alexcomeau.response.googleGeocoding.GoogleGeocodingStruct;
import com.alexcomeau.response.weekforecast.Daily;
import com.alexcomeau.response.weekforecast.Weekly;
import com.alexcomeau.utils.Debug;
import com.alexcomeau.utils.UnitConverter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DailyWeatherEmbed {
    public static MessageEmbed buildEmbed(Weekly w, GoogleGeocodingStruct geo, WeatherArg unit){
        EmbedBuilder eb = new EmbedBuilder();
        String city = "error";
        for(AddressComponent ac : geo.getResults()[0].getAddress_components()){
            for(String s : ac.getTypes()){
                if(s.equals("locality")){
                    city = ac.getLong_name();
                    break;
                }
            }
            if(!city.equals("error")){
                break;
            }
        }

        String[] fields = fieldBuilder(w.getDaily(), unit);
        eb.setTitle("7-day weather")
              .setDescription(city)
              .setColor(new Color(0xB65421))
              .setTimestamp(LocalDateTime.now())
              .setFooter("if this information is inaccurate try being more specific with your location name", "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
              .setThumbnail("https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
              .setAuthor("author name", null, "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
              .addField(LocalDateTime.now().getDayOfWeek().name(), "weather, high, low", false)
              .addField(LocalDateTime.now().plusDays(1).getDayOfWeek().name(), fields[0], false)
              .addField(LocalDateTime.now().plusDays(2).getDayOfWeek().name(), fields[1], false)
              .addField(LocalDateTime.now().plusDays(3).getDayOfWeek().name(), fields[2], false)
              .addField(LocalDateTime.now().plusDays(4).getDayOfWeek().name(), fields[3], false)
              .addField(LocalDateTime.now().plusDays(5).getDayOfWeek().name(), fields[4], false)
              .addField(LocalDateTime.now().plusDays(5).getDayOfWeek().name(), fields[5], false)
              .addField(LocalDateTime.now().plusDays(6).getDayOfWeek().name(), fields[6], false);
        Debug.debug("character length is: " + eb.toString().length());


        return eb.build();
    }

    private static String[] fieldBuilder(Daily[] dailyArray, WeatherArg unit){
        ArrayList<String> fields = new ArrayList<>();
        int i = 0;
        for(Daily d : dailyArray){
            String temp = d.getWeather()[0].getMain() + "\n";
            temp += "a high of " + UnitConverter.convertTemp(d.getTemp().getMax(), unit) + " " + unit.getLetter()+ ",\n";
            temp += "a low of " + UnitConverter.convertTemp(d.getTemp().getMin(), unit) + " " + unit.getLetter();
            fields.add(i, temp);
            i++;
        }
        return fields.toArray(new String[fields.size()]);
    }
}

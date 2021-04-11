package com.alexcomeau.bot.embeds;

import com.alexcomeau.response.weekforecast.Daily;
import com.alexcomeau.response.weekforecast.Weekly;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DailyWeatherEmbed {
    public static MessageEmbed buildEmbed(Weekly w){
        EmbedBuilder eb = new EmbedBuilder();
        String[] fields = fieldBuilder(w.getDaily());
          eb.setTitle("7-day weather")
              .setDescription("address")
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

        return eb.build();
    }

    private static String[] fieldBuilder(Daily[] dailyArray){
        ArrayList<String> fields = new ArrayList<>();
        int i = 0;
        for(Daily d : dailyArray){
            String temp = d.getWeather()[0].getDescription() + "\n";
            temp += "a high of" + (9/5*(d.getT().getMax() - 237)) + " F" + ",\n";
            temp += "a low of" + (9/5*(d.getT().getMin() - 237)) + " F";
            fields.add(i, temp);
            i++;
        }
        return fields.toArray(new String[fields.size()]);
    }
}

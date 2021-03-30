package com.alexcomeau.bot.embeds;

import com.alexcomeau.response.weekforecast.Weekly;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.time.LocalDateTime;

public class DailyWeatherEmbed {
    public static MessageEmbed buildEmbed(Weekly w){
        EmbedBuilder eb = new EmbedBuilder();

          eb.setTitle("7-day weather")
                .setDescription("address")
                .setColor(new Color(0xB65421))
                .setTimestamp(LocalDateTime.now())
                .setFooter("if this information is inaccurate try being more specific with your location name", "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
                .setThumbnail("https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
                .setAuthor("author name", null, "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
                .addField("date", "weather, high, low", false)
                .addField("date", "weather, high, low", false)
                .addField("date", "weather, high, low", false)
                .addField("date", "weather, high, low", false)
                .addField("date", "weather, high, low", false)
                .addField("date", "weather, high, low", false)
                .addField("date", "weather, high, low", false);

        return eb.build();
    }
}

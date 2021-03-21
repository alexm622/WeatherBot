package com.alexcomeau;

import com.alexcomeau.response.Response;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;

public class WeatherEmbed {
    private static final String iconTemplate = "http://openweathermap.org/img/wn/$@2x.png";
    private static final String precipTemplate = "\namount in last hour:$\namount in last 3 hours:%";
    private static final String tempTemplate = "temp:$ \nfeels like:%\nmax temp:^\nmin temp:&\npressure:*\nhumidity:!";

    public static MessageEmbed buildEmbeded(Response w, String city){
        String precip;
        String temp;
        EmbedBuilder eb = new EmbedBuilder();
        String icon = iconTemplate.replace("$",w.getWeather()[0].getIcon());

        precip = generatePrecip(w);
        //generate temp
        temp = generateTemp(w);



        eb.setTitle("Your current Weather")
                .setDescription(city)
                .setColor(new Color(4457094))
                .setTimestamp(OffsetDateTime.parse("2021-03-21T17:35:28.660Z"))
                .setThumbnail(icon)
                .setImage(icon)
                .setAuthor("WeatherBot", "https://github.com/alexm622/WeatherBot", "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
                .addField(":bar_chart:", "current weather:" + w.getWeather()[0].getMain() + precip, true)
                .addField(":thermometer:", temp, true)
                .addField(":eyeglasses:", "visibility:" + w.getVisibility(), true)
                .addField(":dash:", "wind speed:" + w.getWind().getSpeed() +"\ndirection:" + w.getWind().getDeg(), true)
                .addField(":cloud:", "clouds:" + w.getClouds().getAll() + "% ", true)
                .addField(":sun_with_face:", "Sunset:" + getTime(w.getSys().getSunset()) + "\nSunrise:" + getTime(w.getSys().getSunrise()), true);
        return eb.build();
    }

    private static String generateTemp(Response w) {
        return tempTemplate.replace("$", Float.toString(w.getMain().getTemp()))
                .replace("%", Float.toString(w.getMain().getFeels_like()))
                .replace("^", Float.toString(w.getMain().getTemp_max()))
                .replace("&", Float.toString(w.getMain().getTemp_min()))
                .replace("*", Float.toString(w.getMain().getPressure()))
                .replace("!", Float.toString(w.getMain().getHumidity())); //TODO this needs sea level/ground level
    }

    private static String generatePrecip(Response w) {
        //generate precipitation
        String precip = "";
        if(w.getRain() != null && w.getRain().getOne() != 0 || w.getRain().getThree() !=0){
            precip = precipTemplate.replace("$ ", Float.toString(w.getRain().getOne())).replace("%", Float.toString(w.getRain().getThree()));
        }else if(w.getSnow() != null && w.getSnow().getOne() != 0 || w.getSnow().getThree() != 0){
            precip = precipTemplate.replace("$ ", Float.toString(w.getSnow().getOne())).replace("%", Float.toString((w.getSnow().getThree())));
        }
        Debug.debug("precip is " + precip);
        return "";
    }

    private static String getTime(long unixSeconds){
        // convert seconds to milliseconds
        Date date = new java.util.Date(unixSeconds*1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }
}

package com.alexcomeau.bot.embeds;

import com.alexcomeau.response.currentweather.Rain;
import com.alexcomeau.response.currentweather.Response;
import com.alexcomeau.response.currentweather.Snow;
import com.alexcomeau.response.geocoding.AddressComponent;
import com.alexcomeau.response.geocoding.GeoCodingStruct;
import com.alexcomeau.utils.Debug;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class CurrentWeatherEmbed {
    private static final String iconTemplate = "http://openweathermap.org/img/wn/$@2x.png";
    private static final String precipTemplate = "\namount in last hour:$\namount in last 3 hours:%";
    private static final String tempTemplate = "temp:$ \nfeels like:%\nmax temp:^\nmin temp:&\npressure:*\nhumidity:!";

    public static MessageEmbed buildEmbeded(Response w, GeoCodingStruct geo){
        String precip;
        String temp;
        EmbedBuilder eb = new EmbedBuilder();
        String icon = iconTemplate.replace("$",w.getWeather()[0].getIcon());

        precip = generatePrecip(w);
        //generate temp
        temp = generateTemp(w);

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


        eb.setTitle("Your current Weather")
                .setDescription(city)
                .setColor(new Color(4457094))
                .setTimestamp(LocalDateTime.now())
                .setThumbnail(icon)
                .setImage(icon)
                .setAuthor("WeatherBot", "https://github.com/alexm622/WeatherBot", "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg")
                .addField(":bar_chart:", "current weather:" + w.getWeather()[0].getMain() + precip, true)
                .addField(":thermometer:", temp, true)
                .addField(":eyeglasses:", "visibility:" + w.getVisibility(), true)
                .addField(":dash:", "wind speed:" + w.getWind().getSpeed() +"\ndirection:" + w.getWind().getDeg(), true)
                .addField(":cloud:", "clouds:" + w.getClouds().getAll() + "% ", true)
                .addField(":sun_with_face:", "Sunset:" + getTime(w.getSys().getSunset()) + "\nSunrise:" + getTime(w.getSys().getSunrise()), true)
                .setFooter("if this information is inaccurate try being more specific with your location name", "https://www.noaa.gov/sites/default/files/styles/crop_394x394/public/thumbnails/image/FocusArea__Weather-02.jpg");
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
        boolean rain = true;
        boolean snow = true;
        try{
            w.getRain();
        }catch(NullPointerException e){
            Debug.debug("rain was null");
            w.setRain(new Rain());
            rain = false;
        }
        try{
            w.getSnow();
        }catch(NullPointerException e){
            Debug.debug("snow was null");
            w.setSnow(new Snow());
            snow=false;
        }

        if(rain){
            precip = precipTemplate.replace("$ ", Float.toString(w.getRain().getOne())).replace("%", Float.toString(w.getRain().getThree()));
        }else if(snow){
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

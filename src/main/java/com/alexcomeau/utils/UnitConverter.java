package com.alexcomeau.utils;

import com.alexcomeau.bot.commands.weather.WeatherArg;

public class UnitConverter {
    public static float toF(float k){
        return ((float) Math.round((9/5*(k - 237))*10))/10;
    }

    public static float toC(float k){
        return ((float) Math.round((k - 237))*10)/10;
    }

    public static float toK(float k){
        return ((float) Math.round(k*10))/10;
    }

    public static float convertTemp(float k, WeatherArg unit){
        switch(unit){
            case SCIENTIFIC:
                return toK(k);
            case METRIC:
                return toC(k);
            case IMPERIAL:
                return toF(k);
            default:
                return k;
        }
    }
}

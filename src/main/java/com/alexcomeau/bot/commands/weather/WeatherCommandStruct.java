package com.alexcomeau.bot.commands.weather;

import com.alexcomeau.bot.commands.CommandStruct;
import com.alexcomeau.bot.commands.CommandType;

import java.util.ArrayList;

public class WeatherCommandStruct {
        public CommandType command;
        public WeatherArg[] args;
        public String input;

        public WeatherCommandStruct fromCommandStruct(CommandStruct cs){
            this.command = cs.command;
            this.input = cs.input;
            this.args = parseArgs(cs.args);

            return this;
        }

        private WeatherArg[] parseArgs(Character[] charArray){
            ArrayList<WeatherArg> out = new ArrayList<>();

            for(char c : charArray){
                for(WeatherArg w : WeatherArg.values()){
                    if(c == w.getLetter()){
                        out.add(w);
                        break;
                    }
                }
            }

            return out.toArray(new WeatherArg[out.size()]);
        }


}

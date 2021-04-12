package com.alexcomeau.utils;

import com.alexcomeau.bot.commands.CommandStruct;
import com.alexcomeau.bot.commands.CommandType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommandParser {
    public static CommandStruct parseArgs(String command){
        CommandStruct c = new CommandStruct();
        HashMap<String, String> subs = new HashMap<>();

        c.args = new String[]{"invalid"};
        c.command = CommandType.UNKNOWN;
        c.input = "invalid";

        String[] split = command.split(" ");

        if(split.length < 1){

            return c;
        }
        if(split.length == 1){
            c.command = matchCommandEnums(split[0]);
            return c;
        }

        c.command = matchCommandEnums(split[0]);

        if(split.length >= 2){
            String temp = split[1];
            if(temp.startsWith("-")){
                temp.replaceAll("-", "");
                ArrayList<String> args = new ArrayList<>();
                for(Character character : temp.toCharArray()){
                    args.add(character.toString());
                }
                c.args = args.toArray(new String[args.size()]);
            }else{
                StringBuilder sb = new StringBuilder();
                for(String s : Arrays.copyOfRange(split, 1, split.length)){
                    sb.append(s + " ");
                }
                c.input = sb.toString().trim();
            }
            if(split.length == 2){
                return c;
            }

            if(c.args.equals(new String[]{"invalid"})){
                return c;
            }else{
                StringBuilder sb = new StringBuilder();
                for(String s : Arrays.copyOfRange(split, 2, split.length)){
                    sb.append(s + " ");
                }
                c.input = sb.toString().trim();
            }
        }
        return c;
    }

    private static CommandType matchCommandEnums(String command){
        for(CommandType c : CommandType.values()){
            if(c.getPrefix().equals(command.trim())){
                return c;
            }
        }
        return CommandType.UNKNOWN;
    }

}

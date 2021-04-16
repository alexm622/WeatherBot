package com.alexcomeau.bot.commands;

public enum CommandType {
    WEATHER("&w"),
    GOOSE("&goose"),
    RAT("&rat"),
    UNKNOWN(""),;

    private String prefix;
    CommandType(String prefix){
        this.prefix = prefix;
    }
    public String getPrefix(){
        return this.prefix;
    }
}

package com.alexcomeau.bot.commands;

public enum CommandType {
    WEATHER("&w"),
    GOOSE("&goose"),
    UNKNOWN(null);

    private String prefix;
    CommandType(String prefix){
        this.prefix = prefix;
    }
    public String getPrefix(){
        return this.prefix;
    }
}

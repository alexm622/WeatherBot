package com.alexcomeau.bot.commands.weather;

public enum WeatherArg {
    DAILY('d', WeatherArgType.SUBCOMMAND),
    WEEKLY('d', WeatherArgType.SUBCOMMAND),
    IMPERIAL('f', WeatherArgType.UNITS),
    CELSIUS('c', WeatherArgType.UNITS),
    KELVIN('k', WeatherArgType.UNITS),
    NONE('i', WeatherArgType.NOTHING),
    UNKNOWN(' ', WeatherArgType.UNKNOWN);

    private char prefix;
    private WeatherArgType type;
    WeatherArg(char prefix, WeatherArgType type){
        this.prefix = prefix;
        this.type = type;
    }
    public char getLetter(){
        return this.prefix;
    }
    public WeatherArgType getType(){return this.type;}
}

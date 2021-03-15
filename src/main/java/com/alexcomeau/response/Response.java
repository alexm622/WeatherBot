package com.alexcomeau.response;

public class Response {
    private Coord coord;
    private Weather[] weather;
    private String base;
    private MainResponse main;
    private long visbility;
    private Wind wind;
    private Clouds clouds; // cloud percentage
    private Rain rain;
    private Snow snow;
    private long dt; //date-time
    private SysResponse sys; //internal stuff and sun
    private long timezone;
    private long id;
    private String name;
    private int cod;
}
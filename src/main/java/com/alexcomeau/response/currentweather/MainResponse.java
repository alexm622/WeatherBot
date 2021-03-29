package com.alexcomeau.response.currentweather;

public class MainResponse {
    private float temp;
    private float feels_like;
    private float temp_min;
    private float temp_max;
    private long pressure;
    private long sea_level;
    private long grnd_level;
    private int humidity;

    public float getTemp(){
        return this.temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeels_like(){
        return this.feels_like;
    }

    public void setFeels_like(float feels_like) {
        this.feels_like = feels_like;
    }

    public float getTemp_min() {
        return this.temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max(){
        return this.temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public long getPressure(){
        return this.pressure;
    }

    public void setPressure(long pressure) {
        this.pressure = pressure;
    }

    public long getSea_level(){
        return this.sea_level;
    }

    public void setSea_level(long sea_level) {
        this.sea_level = sea_level;
    }

    public long getGrnd_level(){
        return this.grnd_level;
    }

    public void setGrnd_level(long grnd_level) {
        this.grnd_level = grnd_level;
    }

    public int getHumidity(){
        return this.humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}

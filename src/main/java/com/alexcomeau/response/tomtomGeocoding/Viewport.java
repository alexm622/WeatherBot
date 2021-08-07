package com.alexcomeau.response.tomtomGeocoding;

public class Viewport {
    private Location topLeftPoint;
    private Location btmRIghtPoint;

    public Location getTopLeftPoint() {
        return topLeftPoint;
    }

    public void setTopLeftPoint(Location topLeftPoint) {
        this.topLeftPoint = topLeftPoint;
    }

    public Location getBtmRIghtPoint() {
        return btmRIghtPoint;
    }

    public void setBtmRIghtPoint(Location btmRIghtPoint) {
        this.btmRIghtPoint = btmRIghtPoint;
    }
}

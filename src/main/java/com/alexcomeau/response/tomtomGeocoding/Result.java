package com.alexcomeau.response.tomtomGeocoding;

public class Result {
    private String type;
    private String id;
    private double score;
    private double dist;
    private Address address;
    private Location position;
    private Viewport viewport;
    private EntryPoint[] entryPoints;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    public EntryPoint[] getEntryPoints() {
        return entryPoints;
    }

    public void setEntryPoints(EntryPoint[] entryPoints) {
        this.entryPoints = entryPoints;
    }
}

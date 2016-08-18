package com.blackflag.fairbaseex;

/**
 * Created by BlackFlag on 8/17/2016.
 */
public class Location {
    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Location(Double lat, Double lon) {

        this.lat = lat;
        this.lon = lon;
    }

    public Location() {

    }

    Double lat;
    Double lon;
}

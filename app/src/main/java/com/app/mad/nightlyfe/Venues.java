package com.app.mad.nightlyfe;

/**
 * Created by Dennis on 2016-05-14.
 */
public class Venues {
    private String venueName;
    private String genre;
    private String address;
    private int entrance_fee;

    public Venues(){

    }

    public Venues(String venueName, String genre, String address, int entrance_fee){
        this.venueName = venueName;
        this.genre = genre;
        this.address = address;
        this.entrance_fee = entrance_fee;
    }

    public String getVenueName(){
        return venueName;
    }

    public String getGenre(){
        return genre;
    }

    public String getAddress(){
        return address;
    }

    public int getEntrance_fee(){
        return entrance_fee;
    }
}

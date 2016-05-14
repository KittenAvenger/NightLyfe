package com.app.mad.nightlyfe;

/**
 * Created by Dennis on 2016-05-14.
 */
public class Venues {
    private String venueName;
    private String genre;
    private String address;

    public Venues(){

    }

    public Venues(String venueName, String genre, String address){
        this.venueName = venueName;
        this.genre = genre;
        this.address = address;
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
}

package com.example.p4_daa_alexandre.data;

import java.util.List;

public class Meeting {

    private long id;
    private String title;
    private Integer hour;
    private String place;
    private String about;
    private String participant;

    public Meeting(long id, String title, Integer hour, String place, String about, String participant) {
        this.id = id;
        this.title = title;
        this.hour = hour;
        this.place = place;
        this.about = about;
        this.participant = participant;
    }
}

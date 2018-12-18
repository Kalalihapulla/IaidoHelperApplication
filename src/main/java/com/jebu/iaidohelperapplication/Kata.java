package com.jebu.iaidohelperapplication;

import java.util.ArrayList;

/**
 * Created by Jebu on 5.3.2018.
 */

//Model for the katas/techniques.
public class Kata {


    private String name;
    private int kataNumber;
    private String description;
    private String videoId;
    private ArrayList<GradingPoint> gradingPoints;


    public Kata(String name,int kataNumber, String description, String videoId, ArrayList<GradingPoint> gradingPoints) {
        this.name = name;
        this.description = description;
        this.videoId = videoId;
        this.gradingPoints = gradingPoints;

    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<GradingPoint> getGradingPoints  () {
        return this.gradingPoints;
    }

    public String getVideoId() {
        return this.videoId;
    }

    public int getKataNumber() {
        return this.kataNumber;
    }

}

package com.jebu.iaidohelperapplication;

//Grading point model for the katas/techniques.
public class GradingPoint {

    private int pointNumber;
    private String timeStamp;
    private String description;
    private Boolean checked;


    public GradingPoint(int pointNumber, String timeStamp, String description, Boolean checked) {
        this.pointNumber = pointNumber;
        this.timeStamp = timeStamp;
        this.description = description;
        this.checked = checked;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTimeStamp() { return this.timeStamp; }


}

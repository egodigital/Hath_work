package com.example.easycar;


import com.google.android.gms.common.internal.FallbackServiceBroker;

public class Vehicle {
    public double distance;
    public int battery;
    public int temperature;
    public String location;
    public int power;
    public int humility;
    public int seath;
    public double seata;
    public double mirr;
    public boolean air;
    public boolean motor;

    Vehicle(){
        seata=Math.random()*60;
        seath=(int)(Math.random()*30);
        mirr=(int)(Math.random()*60);
        humility=(int)(Math.random()*90);
        air= false;
        motor=false;

    }

    public String getLocation(){
        return location;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public int getBattery() {
        return battery;
    }
    public void setBattery(int battery) {
        this.battery = battery;
    }
}

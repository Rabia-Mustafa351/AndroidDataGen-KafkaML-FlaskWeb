package com.example.datacollectionapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DataModel {

    @SerializedName("x")
    @Expose
    private float x;
    @SerializedName("y")
    @Expose
    private float y;
    @SerializedName("z")
    @Expose
    private float z;
    @SerializedName("groupname")
    @Expose
    private String groupname;
    @SerializedName("label")
    @Expose
    private String label;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

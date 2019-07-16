package com.example.spacexrocketlauncher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Engines {

    //Attributes
    @SerializedName("number")
    @Expose
    private String number;

    @SerializedName("type")
    @Expose
    private String type;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

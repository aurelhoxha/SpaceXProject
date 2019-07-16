package com.example.spacexrocketlauncher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rocket {

    //Attributes
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("rocket_name")
    @Expose
    private String rocket_name;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("active")
    @Expose
    private String active;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("engines")
    private Engines engines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRocketName() {
        return rocket_name;
    }

    public void setRocketName(String rocket_name) {
        this.rocket_name = rocket_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Engines getEngine() {
        return engines;
    }

    public void setEngine(Engines engines) {
        this.engines = engines;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

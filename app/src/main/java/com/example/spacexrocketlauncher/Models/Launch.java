package com.example.spacexrocketlauncher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Launch {

    //Attributes
    @SerializedName("mission_name")
    @Expose
    private String mission_name;

    @SerializedName("launch_year")
    @Expose
    private String launch_year;

    @SerializedName("launch_date_local")
    @Expose
    private String launch_date_local;

    @SerializedName("launch_success")
    @Expose
    private String launch_success;

    @SerializedName("rocket")
    private Rocket rocket;

    @SerializedName("links")
    private Links links;

    public String getMission_name() {
        return mission_name;
    }

    public void setMission_name(String mission_name) {
        this.mission_name = mission_name;
    }

    public String getLaunch_year() {
        return launch_year;
    }

    public void setLaunch_year(String launch_year) {
        this.launch_year = launch_year;
    }

    public String getLaunch_date_local() {
        return launch_date_local;
    }

    public void setLaunch_date_local(String launch_date_local) {
        this.launch_date_local = launch_date_local;
    }

    public String getLaunch_success() {
        return launch_success;
    }

    public void setLaunch_success(String launch_success) {
        this.launch_success = launch_success;
    }

    public Rocket getRocket() {
        return rocket;
    }

    public void setRocket(Rocket rocket) {
        this.rocket = rocket;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }
}

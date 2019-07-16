package com.example.spacexrocketlauncher.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links {
    @SerializedName("mission_patch_small")
    @Expose
    private String mission_patch_small;

    public String getMission_patch_small() {
        return mission_patch_small;
    }

    public void setMission_patch_small(String mission_patch_small) {
        this.mission_patch_small = mission_patch_small;
    }
}

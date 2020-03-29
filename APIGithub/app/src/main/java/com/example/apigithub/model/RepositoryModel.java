package com.example.apigithub.model;

import com.google.gson.annotations.SerializedName;

public class RepositoryModel {

    @SerializedName("name")
    private String name;

    @SerializedName("display_name")
    private String display_name;

    @SerializedName("short_description")
    private String short_description;

    @SerializedName("description")
    private String description;

    @SerializedName("created_by")
    private String created_by;

    @SerializedName("released")
    private String released;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    @SerializedName("featured")
    private boolean  featured;

    @SerializedName("curated")
    private boolean  curated;

    @SerializedName("score")
    private String score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public boolean isCurated() {
        return curated;
    }

    public void setCurated(boolean curated) {
        this.curated = curated;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

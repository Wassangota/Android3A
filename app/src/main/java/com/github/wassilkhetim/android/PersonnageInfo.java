package com.github.wassilkhetim.android;

import java.util.List;

public class PersonnageInfo {

    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationInfo origin;
    private LocationInfo location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public LocationInfo getOrigin() {
        return origin;
    }

    public LocationInfo getLocation() {
        return location;
    }

    public String getImage() {
        return image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated(){
        return created;
    }

}

package ru.technotrack.onlinecinema;

import java.util.HashSet;
import java.util.Set;

public class Movie {
    public enum TAG {
        COMEDY, DRAMA, ACTION, HOT
    }

    private boolean free;
    private String price;
    private String name;
    private String info;
    private String actors;
    private Set<TAG> tags = new HashSet<>();
    private int picture;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public void addTag(TAG tag) {
        tags.add(tag);
    }

    public boolean containTag(TAG tag) {
        return tags.contains(tag);
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}

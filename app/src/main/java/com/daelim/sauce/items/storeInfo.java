package com.daelim.sauce.items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class storeInfo {

    @Expose
    @SerializedName("temholiday") private String temholiday;
    @Expose
    @SerializedName("scate") private String scate;
    @Expose
    @SerializedName("breaktime") private String breaktime;
    @Expose
    @SerializedName("address") private String address;
    @Expose
    @SerializedName("lon") private String lon;
    @Expose
    @SerializedName("holiday") private String holiday;
    @Expose
    @SerializedName("opening") private String opening;
    @Expose
    @SerializedName("boss_name") private String boss_name;
    @Expose
    @SerializedName("number") private String number;
    @Expose
    @SerializedName("store_phone") private String store_phone;
    @Expose
    @SerializedName("intro") private String intro;
    @Expose
    @SerializedName("store_name") private String store_name;
    @Expose
    @SerializedName("id") private String id;
    @Expose
    @SerializedName("created_date") private String created_date;
    @Expose
    @SerializedName("lat") private String lat;


    public String getTemholiday() {
        return temholiday;
    }

    public void setTemholiday(String temholiday) {
        this.temholiday = temholiday;
    }

    public String getScate() {
        return scate;
    }

    public void setScate(String scate) {
        this.scate = scate;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getBoss_name() {
        return boss_name;
    }

    public void setBoss_name(String boss_name) {
        this.boss_name = boss_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStore_phone() {
        return store_phone;
    }

    public void setStore_phone(String store_phone) {
        this.store_phone = store_phone;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}

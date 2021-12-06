package com.daelim.sauce.items;

import android.content.Intent;
import android.provider.ContactsContract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class mypageinfo {

    @Expose
    @SerializedName("userid") private String userid;
    @Expose
    @SerializedName("email") private String email;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }
}


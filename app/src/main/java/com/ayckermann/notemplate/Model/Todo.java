package com.ayckermann.notemplate.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Todo implements Serializable {
    public String uid, userId, judul;
    public ArrayList<Boolean> listCheck;
    public ArrayList<String> listText;

    public Todo(){

    }

    public Todo(String userId, String judul, ArrayList<Boolean> listCheck, ArrayList<String> listText) {
        this.userId = userId;
        this.judul = judul;
        this.listCheck = listCheck;
        this.listText = listText;
    }


}

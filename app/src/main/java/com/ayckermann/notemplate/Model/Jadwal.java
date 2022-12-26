package com.ayckermann.notemplate.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Jadwal implements Serializable {

    public String uid, userId, judul, monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    public Jadwal(){

    }

    public Jadwal(String userId, String judul, String monday, String tuesday, String wednesday, String thursday, String friday, String saturday, String sunday) {
        this.userId = userId;
        this.judul = judul;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Jadwal [judul=").append(judul).
                append(", monday=").append(monday).
                append(", tuesday=").append(tuesday).
                append(", wednesday=").append(wednesday).
                append(", thursday=").append(thursday).
                append(", friday=").append(friday).
                append(", saturday=").append(saturday).
                append(", sunday=").append(sunday).
                append("]");
        return builder.toString();
    }

    public Map<String, String> asMap() {
        Map<String, String> map = new HashMap<String, String>();
        String stringRepresentation = this.toString();
        if (stringRepresentation == null || stringRepresentation.trim().equals("")) {
            return map;
        }
        if (stringRepresentation.contains("[")) {
            int index = stringRepresentation.indexOf("[");
            stringRepresentation = stringRepresentation.substring(index + 1, stringRepresentation.length());
        }
        if (stringRepresentation.endsWith("]")) {
            stringRepresentation = stringRepresentation.substring(0, stringRepresentation.length() - 1);
        }
        String[] commaSeprated = stringRepresentation.split(",");
        for (int i = 0; i < commaSeprated.length; i++) {
            String keyEqualsValue = commaSeprated[i];
            keyEqualsValue = keyEqualsValue.trim();
            if (keyEqualsValue.equals("") || !keyEqualsValue.contains("=")) {
                continue;
            }
            String[] keyValue = keyEqualsValue.split("=", 2);
            if (keyValue.length > 1) {
                map.put(keyValue[0].trim(), keyValue[1].trim());
            }

        }
        return map;
    }
}

package com.ayckermann.notemplate.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Note implements Serializable {

    public String uid, userId, judul, content, tanggal;

    public Note(){

    }
    public Note(String userId, String  judul, String content, String tanggal) {
        this.userId = userId;
        this.judul = judul;
        this.content = content;
        this.tanggal = tanggal;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Note [judul=").append(judul).
                append(", content=").append(content).
                append(", tanggal=").append(tanggal).
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

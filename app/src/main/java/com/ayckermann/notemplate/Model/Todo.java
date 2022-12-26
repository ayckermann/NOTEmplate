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

//    @Override
//    public String toString() {
////        String check="";
////        String text="";
////        for(int i =0; i <listCheck.size();i++){
////            check += ", listCheck.get("+i+")="+listCheck.get(i);
////            text += ", listText.get("+i+")="+ listText.get(i);
////        }
//        StringBuilder builder = new StringBuilder();
//        builder.append("Todo [judul=").append(judul).
////                append(check).append(text).
//                append(", listCheck=").append(listCheck).
//                append(", listText=").append(listText).
//                append("]");
//        return builder.toString();
//    }
//
//    public Map<String, String> asMap() {
//        Map<String, String> map = new HashMap<String, String>();
//        String stringRepresentation = this.toString();
//        if (stringRepresentation == null || stringRepresentation.trim().equals("")) {
//            return map;
//        }
//        if (stringRepresentation.contains("[")) {
//            int index = stringRepresentation.indexOf("[");
//            stringRepresentation = stringRepresentation.substring(index + 1, stringRepresentation.length());
//        }
//        if (stringRepresentation.endsWith("]")) {
//            stringRepresentation = stringRepresentation.substring(0, stringRepresentation.length() - 1);
//        }
//        String[] commaSeprated = stringRepresentation.split(",");
//        for (int i = 0; i < commaSeprated.length; i++) {
//            String keyEqualsValue = commaSeprated[i];
//            keyEqualsValue = keyEqualsValue.trim();
//            if (keyEqualsValue.equals("") || !keyEqualsValue.contains("=")) {
//                continue;
//            }
//            String[] keyValue = keyEqualsValue.split("=", 2);
//            if (keyValue.length > 1) {
//                map.put(keyValue[0].trim(), keyValue[1].trim());
//            }
//
//        }
//        return map;
//    }

}

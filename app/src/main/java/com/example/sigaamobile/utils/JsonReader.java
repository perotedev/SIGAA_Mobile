package com.example.sigaamobile.utils;

import org.json.JSONObject;

public class JsonReader {
    public static JSONObject read(String jsonUri){
//        JSONParser parser = new JSONParser();
        JSONObject object = null;
//        try {
//            object = (JSONObject) parser.parse(new FileReader(jsonUri));
//        } catch (IOException | ParseException e) {
//            e.printStackTrace();
//        }
        return object;
    }
}

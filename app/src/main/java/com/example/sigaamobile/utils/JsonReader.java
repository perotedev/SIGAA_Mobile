package com.example.sigaamobile.utils;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class JsonReader {
    private Activity activity;

    public JsonReader (Activity activity){
        this.activity = activity;
    }

    public JSONArray read(String jsonAssetName, String jsonFileName){
        JSONArray jsonArray = null;
        try {
            // get JSONObject from JSON file
            JSONObject object = new JSONObject(Objects.requireNonNull(loadJSONFromAsset(jsonFileName)));
            // fetch JSONArray named users
            jsonArray = object.getJSONArray(jsonAssetName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    private String loadJSONFromAsset(String jsonFileName) {
        String json = null;
        try {
            InputStream is = this.activity.getAssets().open(jsonFileName);
            System.out.println("IS_ "+is);
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

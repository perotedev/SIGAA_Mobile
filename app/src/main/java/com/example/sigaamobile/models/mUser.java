package com.example.sigaamobile.models;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class mUser {
    private String username, password, token;
    private int userId;

    public mUser(){}

    public mUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", this.username);
        result.put("password", this.password);
        result.put("userId", this.userId);
        return result;
    }
}

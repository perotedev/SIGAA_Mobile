package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mUser {
    private String username, password, token;
    private int userId;

    public mUser(){}
}

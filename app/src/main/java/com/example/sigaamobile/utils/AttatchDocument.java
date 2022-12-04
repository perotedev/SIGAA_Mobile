package com.example.sigaamobile.utils;

import android.net.Uri;

import java.io.File;

public class AttatchDocument {
    private Uri documentDirectoy;
    private String documentName;
    private double documentSize;

    // Setters
    public void setDocumentDirectoy(String documentDirectoy){
        setDocumentName(documentDirectoy);
        this.documentDirectoy = Uri.parse(documentDirectoy);
    }

    private void setDocumentName(String documentName){
        this.documentName = documentName;

    }

    public void setDocumentSize(){
        File file = new File(getDocumentDirectoy().toString());
        this.documentSize =  file.length();
    }

    //Getters
    public Uri getDocumentDirectoy() {
        return this.documentDirectoy;
    }

    public String getDocumentName(){
        String[] documentNameList = this.documentName.split("/");
        int sizeList = documentNameList.length;
        return documentNameList[sizeList-1];
    }

    public double getDocumentSize(){
        return this.documentSize;
    }

    public float getDocumentSizeMb(){
        float documentSizeMb;
        String sizeString = String.valueOf((getDocumentSize()/1024)/1024);
        documentSizeMb = Float.parseFloat(sizeString);
        return documentSizeMb;
    }
}

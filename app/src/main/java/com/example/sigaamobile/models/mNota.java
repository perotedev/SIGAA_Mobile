package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mNota {
    private String tipoNota, undNota;
    private float nota;
    private int notaId;

    public mNota(){}
}
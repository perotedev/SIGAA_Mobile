package com.example.sigaamobile.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class mNotasDisciplina {
    private ArrayList<Integer> notasIds;
    private int disciplinaId;
    private String nomeDisciplina, situacao;

    public mNotasDisciplina(){}
}

package com.example.sigaamobile.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class mAtividadesDisciplina {
    private String nomeDisciplina;
    private ArrayList<Integer> atividadesIds;
    private int disciplinaId;

    public mAtividadesDisciplina(){}
}

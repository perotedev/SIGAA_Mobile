package com.example.sigaamobile.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class mAtividadesDisciplina {
    private String nomeDisciplina;
    private ArrayList<mAtividade> atividades;

    public mAtividadesDisciplina(){}
}

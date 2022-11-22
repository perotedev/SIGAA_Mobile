package com.example.sigaamobile.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class mNotasDisciplina {
    private ArrayList<mNota> notas;
    private int disciplinaId, idAluno;
    private String nomeDisciplina, situacao;

    public mNotasDisciplina(){}
}

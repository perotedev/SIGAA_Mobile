package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mAluno {
    private String nomeAluno, email, matricula;
    private int idAluno, idUser, idCurso, statusAluno, dataEntrada;

    public mAluno(){}
}

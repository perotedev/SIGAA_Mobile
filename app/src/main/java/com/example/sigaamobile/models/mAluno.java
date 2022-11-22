package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mAluno {
    private String nomeAluno, email, matricula, dataEntrada;
    private int idAluno, idUser, idCurso, statusAluno;

    public mAluno(){}
}

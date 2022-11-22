package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mDadosAcademicos {
    private int idAluno, idCurso, ch_obrigatoriaPendente,
            ch_optativaPendente, ch_totalCurriculo, ch_complementarPendente;
    private float cr, mc, mcn;

    public mDadosAcademicos(){}
}

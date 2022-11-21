package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mDadosAcademicos {
    private int idAluno, idCurso, cr, mc, mcn, ch_obrigatoriaPendente,
            ch_optativaPendente, ch_totalCurriculo, ch_complementarPendente;

    public mDadosAcademicos(){}
}

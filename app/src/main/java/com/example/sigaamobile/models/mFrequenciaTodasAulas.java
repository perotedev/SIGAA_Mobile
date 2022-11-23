package com.example.sigaamobile.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class mFrequenciaTodasAulas {
    private ArrayList<mFrequenciaAula> frequenciasAulas;
    private String nomeDisciplina;
    private float percentualFrequencia;
    private int fequencia, aulasMinistradas, idAluno, idFrequencia;

    public mFrequenciaTodasAulas(){}
}

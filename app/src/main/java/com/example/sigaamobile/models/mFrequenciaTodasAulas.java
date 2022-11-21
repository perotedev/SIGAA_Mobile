package com.example.sigaamobile.models;

import java.util.ArrayList;
import lombok.Data;

@Data
public class mFrequenciaTodasAulas {
    private ArrayList<Integer> frequenciasIds;
    private String nomeDisciplina;
    private float percentualFrequencia;
    private int fequencia, aulasMinistradas;

    public mFrequenciaTodasAulas(){}
}

package com.example.sigaamobile.models;

import java.io.File;
import lombok.Data;

@Data
public class mAtividade {
    private String titulo, descricao, status, anexoAtividadeUrl, comentarioProfessor;
    private int atividadeId, statusId, disciplinaId, dataInicio, dataFim;
    private File anexoAtividadeFile;

    public  mAtividade(){}
}

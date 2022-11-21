package com.example.sigaamobile.models;

import java.io.File;
import lombok.Data;

@Data
public class mAtividade {
    private String titulo, descricao, status, anexoAtividadeUrl,
            anexoRespostaUrl, comentarioAluno, comentarioProfessor;
    private int statusId, dataInicio, dataFim;
    private File anexoAtividadeFile, anexoRespostaFile;

    public  mAtividade(){}
}

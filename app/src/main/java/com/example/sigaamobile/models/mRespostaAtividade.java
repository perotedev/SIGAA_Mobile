package com.example.sigaamobile.models;

import java.io.File;
import lombok.Data;

@Data
public class mRespostaAtividade {
    private int idAluno, idAtividade;
    private String comentarioAluno, comentarioProfessor, anexoRespostaUrl, anexoCorrecaoUrl;
    private File anexoRespostaFile;

    public mRespostaAtividade(){}
}

package com.example.sigaamobile.models;

import lombok.Data;

@Data
public class mQtdAtividadesResumo {
    private int statusId, qtdAtividades;
    private String status;

    public mQtdAtividadesResumo(){}

    public mQtdAtividadesResumo(int statusId, int qtdAtividades, String status){
        this.statusId = statusId;
        this.qtdAtividades = qtdAtividades;
        this.status = status;
    }
}

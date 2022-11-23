package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividade;
import com.example.sigaamobile.utils.DateTransform;

import java.util.List;

public class DescricaoAtividadesAdapter extends ArrayAdapter<mAtividade> {
    private final List<mAtividade> listaAtividade;
    private final Context context;

    public DescricaoAtividadesAdapter(List<mAtividade> listaAtividade, Context context) {
        super(context, R.layout.card_item_atividade, listaAtividade);
        this.listaAtividade = listaAtividade;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mAtividade atividade = this.listaAtividade.get(position);
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(
                    R.layout.card_item_atividade,
                    parent,
                    false
            );
        }

        TextView titulo = view.findViewById(R.id.atividade_title);
        TextView descricao = view.findViewById(R.id.atividade_descricao);
        TextView dataInicio = view.findViewById(R.id.data_inicio_atividade);
        TextView dataFim = view.findViewById(R.id.data_fim_atividade);
        TextView status = view.findViewById(R.id.status_da_atividade);

        titulo.setText(atividade.getTitulo());
        descricao.setText(atividade.getDescricao());
        dataInicio.setText(DateTransform.transformToStringDate(atividade.getDataInicio()));
        dataFim.setText(DateTransform.transformToStringDate(atividade.getDataFim()));
        status.setText(atividade.getStatus());

        return view;
    }
}

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
import com.example.sigaamobile.models.mFrequenciaAula;
import com.example.sigaamobile.utils.DateTransform;

import java.util.List;

public class TabelaAulasAdapter extends ArrayAdapter<mFrequenciaAula> {
    private final List<mFrequenciaAula> listaFrequencia;
    private final Context context;

    public TabelaAulasAdapter(List<mFrequenciaAula> listaFrequencia, Context context) {
        super(context, R.layout.card_item_aula, listaFrequencia);
        this.listaFrequencia = listaFrequencia;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mFrequenciaAula mFrequenciaAula = this.listaFrequencia.get(position);
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(
                    R.layout.card_item_aula,
                    parent,
                    false
            );
        }

        TextView data = view.findViewById(R.id.data_aula);
        TextView situacao = view.findViewById(R.id.situacao_na_aula);
        String situacaoAula = "Presente";

        if (mFrequenciaAula.getQtdFaltas() > 0){
            situacaoAula = "("+mFrequenciaAula.getQtdFaltas()+") falta(s)";
        }

        data.setText(DateTransform.transformToStringDate(mFrequenciaAula.getDataAula(), DateTransform.SECONDS));
        situacao.setText(situacaoAula);

        return view;
    }
}

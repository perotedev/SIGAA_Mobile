package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mFrequenciaAula;
import com.example.sigaamobile.utils.DateTransform;
import java.util.List;

public class TabelaAulasAdapter extends RecyclerView.Adapter<TabelaAulasAdapter.TabelaAulasHolder> {
    private final List<mFrequenciaAula> listaFrequencia;
    private final Context context;

    public TabelaAulasAdapter(List<mFrequenciaAula> listaFrequencia, Context context) {
        this.listaFrequencia = listaFrequencia;
        this.context = context;
    }

    @NonNull
    @Override
    public TabelaAulasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_aula, parent, false);
        return new TabelaAulasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TabelaAulasHolder holder, int position) {
        mFrequenciaAula mFrequenciaAula = this.listaFrequencia.get(position);

        if (position%2 != 0){
            holder.itemView.setBackgroundResource(R.color.white_transparent);
        }

        TextView data = holder.itemView.findViewById(R.id.data_aula);
        TextView situacao = holder.itemView.findViewById(R.id.situacao_na_aula);
        String situacaoAula = "Presente";

        if (mFrequenciaAula.getQtdFaltas() > 0){
            situacaoAula = "("+mFrequenciaAula.getQtdFaltas()+") falta(s)";
        }

        data.setText(DateTransform.transformToStringDate(mFrequenciaAula.getDataAula()));
        situacao.setText(situacaoAula);
    }

    @Override
    public int getItemCount() {
        return this.listaFrequencia.size();
    }


    public static class TabelaAulasHolder extends RecyclerView.ViewHolder{
        public TabelaAulasHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}

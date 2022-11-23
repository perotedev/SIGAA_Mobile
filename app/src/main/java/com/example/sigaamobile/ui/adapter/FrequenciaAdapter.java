package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mFrequenciaTodasAulas;
import com.example.sigaamobile.utils.AnimateChangeHeight;

import java.util.ArrayList;
import java.util.List;

public class FrequenciaAdapter extends RecyclerView.Adapter<FrequenciaAdapter.FrequenciaHolder> {
    private final List<mFrequenciaTodasAulas> listaFrequencia;
    private final Context context;
    private final ArrayList<Integer> maxHeightCards;

    public FrequenciaAdapter(List<mFrequenciaTodasAulas> listaFrequencia, Context context) {
        this.listaFrequencia = listaFrequencia;
        this.context = context;
        this.maxHeightCards = new ArrayList<>();
    }

    @NonNull
    @Override
    public FrequenciaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_frequencia, parent, false);
        return new FrequenciaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrequenciaHolder holder, int position) {
        mFrequenciaTodasAulas mFrequenciaTodasAulas = this.listaFrequencia.get(position);

        RelativeLayout listagemAulas = holder.itemView.findViewById(R.id.relative_frequencia_content);
        ImageView arrowDown = holder.itemView.findViewById(R.id.arrow_down_frequencia);
        TextView disciplina_frencia_card = holder.itemView.findViewById(R.id.disciplina_frencia_card);
        TextView frequencia_aluno_value = holder.itemView.findViewById(R.id.frequencia_aluno_value);
        TextView frequencia_aulas_minsitradas_value = holder.itemView.findViewById(R.id.frequencia_aulas_minsitradas_value);
        TextView porcentagem = holder.itemView.findViewById(R.id.porcentagem_aluno_value);
        String porcentagemFrequencia = mFrequenciaTodasAulas.getPercentualFrequencia()+"%";

        if (position == 0){
            arrowDown.setRotation(180);
        }

        if (mFrequenciaTodasAulas.getPercentualFrequencia() < 60){
            porcentagem.setTextColor(ContextCompat.getColor(context, R.color.bgError));
        }

        disciplina_frencia_card.setText(String.valueOf(mFrequenciaTodasAulas.getNomeDisciplina()));
        frequencia_aluno_value.setText(String.valueOf(mFrequenciaTodasAulas.getFrequencia()));
        frequencia_aulas_minsitradas_value.setText(String.valueOf(mFrequenciaTodasAulas.getAulasMinistradas()));
        porcentagem.setText(porcentagemFrequencia);

        RecyclerView listaAulas = holder.itemView.findViewById(R.id.recycler_view_aulas);
        listaAulas.setAdapter(
                new TabelaAulasAdapter(
                        mFrequenciaTodasAulas.getFrequenciasAulas(),
                        context
                )
        );
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
        listaAulas.setLayoutManager(layoutManager);

        this.maxHeightCards.add(position, this.getContentExpHeight(listagemAulas, position));
        System.out.println(this.maxHeightCards);

        holder.itemView.setOnClickListener(v -> {
            this.onClickCard(listagemAulas, arrowDown, position);
        });
    }

    @Override
    public int getItemCount() {
        return this.listaFrequencia.size();
    }

    public static class FrequenciaHolder extends RecyclerView.ViewHolder {
        public FrequenciaHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void onClickCard(RelativeLayout view, ImageView arrowDown, int position){
        if (arrowDown.getRotation() == 180){
            this.mostraListaDeAulas(view, 0);
            arrowDown.animate().rotation(0f).setDuration(300).start();
        } else {
            this.mostraListaDeAulas(view, this.maxHeightCards.get(position));
            arrowDown.animate().rotation(180f).setDuration(300).start();
        }
    }

    private void mostraListaDeAulas(RelativeLayout relativeLayout, int newHeight){
        AnimateChangeHeight animateChangeHeight = new AnimateChangeHeight(relativeLayout, newHeight);
        animateChangeHeight.updateAnimate();
    }

    private int getContentExpHeight(RelativeLayout relativeLayout, int position){
        ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
        relativeLayout.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = relativeLayout.getMeasuredHeight();
        if (position > 0){
            params.height = 0;
        }
//        params.addRule(RelativeLayout.BELOW, R.id.relative_frequencia_title);
        return height;
    }
}

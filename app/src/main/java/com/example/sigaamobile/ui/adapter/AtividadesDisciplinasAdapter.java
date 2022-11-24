package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividade;
import com.example.sigaamobile.models.mAtividadesDisciplina;
import com.example.sigaamobile.models.mQtdAtividadesResumo;
import com.example.sigaamobile.utils.AnimateChangeHeight;

import java.util.ArrayList;
import java.util.List;

public class AtividadesDisciplinasAdapter extends RecyclerView.Adapter<AtividadesDisciplinasAdapter.AtividadesDisciplinasHolder> {
    private final List<mAtividadesDisciplina> atividadesDisciplinasArray;
    private final Context context;
    private final ArrayList<Integer> maxHeightCards;

    public AtividadesDisciplinasAdapter(List<mAtividadesDisciplina> atividadesDisciplinasArray, Context context){
        this.atividadesDisciplinasArray = atividadesDisciplinasArray;
        this.context = context;
        this.maxHeightCards = new ArrayList<>();
    }

    @NonNull
    @Override
    public AtividadesDisciplinasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_atividade, parent, false);
        return new AtividadesDisciplinasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadesDisciplinasHolder holder, int position) {
        mAtividadesDisciplina mAtividadesDisciplina = this.atividadesDisciplinasArray.get(position);
        ArrayList<mAtividade> atividades = mAtividadesDisciplina.getAtividades();

        ImageView arrowDown = holder.itemView.findViewById(R.id.arrow_down_atividade);
        TextView tituloAtividade = holder.itemView.findViewById(R.id.disciplina_atividade_card);
        RelativeLayout semAtividadesView = holder.itemView.findViewById(R.id.relative_no_atividades);
        RelativeLayout qtdAtividades = holder.itemView.findViewById(R.id.relative_atividades_qtd_resumo);
        RelativeLayout listaAtividades = holder.itemView.findViewById(R.id.relative_atividades_content);
        RecyclerView listaResumo = holder.itemView.findViewById(R.id.recycler_view_qtd_atividades);
        ListView listViewAtividades = holder.itemView.findViewById(R.id.list_view_aulas);

        tituloAtividade.setText(mAtividadesDisciplina.getNomeDisciplina());

        ViewGroup.LayoutParams params = semAtividadesView.getLayoutParams();
        params.height = 0;
        semAtividadesView.setLayoutParams(params);
        if (atividades.size() == 0){
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            semAtividadesView.setLayoutParams(params);
        } else {
            listaResumo.setAdapter(
                    new ResumoAtividadesAdapter(
                            this.getQtdAtividadesResumo(mAtividadesDisciplina.getAtividades()),
                            holder.itemView.getContext()
                    )
            );
            LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext());
            listaResumo.setLayoutManager(layoutManager);
            this.showItemContent(qtdAtividades);

            listViewAtividades.setAdapter(
                    new DescricaoAtividadesAdapter(
                            mAtividadesDisciplina.getAtividades(),
                            holder.itemView.getContext()
                    )
            );
        }


        this.maxHeightCards.add(position, this.getContentExpHeight(listaAtividades));

        holder.itemView.setOnClickListener(v -> {
            if (atividades.size() > 0){
                this.onClickCard(listaAtividades, arrowDown, position, qtdAtividades);
            } else {
                Toast.makeText(
                        context,
                        "Sem atividades cadastradas para "+mAtividadesDisciplina.getNomeDisciplina()+"!",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        if (position == 0){
            if (atividades.size() > 0){
                this.onClickCard(listaAtividades, arrowDown, position, qtdAtividades);
            }
        }
    }

    @Override
    public int getItemCount() {
        return this.atividadesDisciplinasArray.size();
    }

    public static class AtividadesDisciplinasHolder extends RecyclerView.ViewHolder {
        public AtividadesDisciplinasHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private ArrayList<mQtdAtividadesResumo> getQtdAtividadesResumo(ArrayList<mAtividade> atividades){
        ArrayList<mQtdAtividadesResumo> arrayQtds = new ArrayList<>();
        int countPendente = 0, countEntregue = 0, countNaoEntregue = 0;

        for (mAtividade mAtividade : atividades) {
            if (mAtividade.getStatusId() == 0){
                countPendente++;
            } else if (mAtividade.getStatusId() == 1){
                countEntregue++;
            } else {
                countNaoEntregue++;
            }
        }

        if (countEntregue > 0) {
            arrayQtds.add(new mQtdAtividadesResumo(
                    0,
                    countEntregue,
                    "Atividade(s) entregue(s)"
            ));
        }

        if (countPendente > 0){
            arrayQtds.add(new mQtdAtividadesResumo(
                    1,
                    countEntregue,
                    "Atividade(s) aguardando entrega"
            ));
        }

        if (countNaoEntregue > 0){
            arrayQtds.add(new mQtdAtividadesResumo(
                    2,
                    countEntregue,
                    "Atividade(s) não entregue(s)"
            ));
        }

        System.out.println("arrayQtd: "+arrayQtds);
        return arrayQtds;
    }

    private void showItemContent(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(params);
    }

    private void onClickCard(View view, ImageView arrowDown, int position, RelativeLayout listaResumo){
        ViewGroup.LayoutParams params = listaResumo.getLayoutParams();
        if (arrowDown.getRotation() == 180){
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            this.mostraListaAtividades(view, 0);
            arrowDown.animate().rotation(0f).setDuration(300).start();
        } else {
            params.height = 0;
            this.mostraListaAtividades(view, this.maxHeightCards.get(position));
            arrowDown.animate().rotation(180f).setDuration(300).start();
        }
        listaResumo.setLayoutParams(params);
    }

    private void mostraListaAtividades(View view, int newHeight){
        AnimateChangeHeight animateChangeHeight = new AnimateChangeHeight(view, newHeight);
        animateChangeHeight.updateAnimate();
    }

    private int getContentExpHeight(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = view.getMeasuredHeight() + view.getPaddingTop() + view.getPaddingBottom();
        params.height = 0;
        view.setLayoutParams(params);
        return height;
    }
}

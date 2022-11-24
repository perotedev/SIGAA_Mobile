package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mQtdAtividadesResumo;

import java.util.List;

public class ResumoAtividadesAdapter extends RecyclerView.Adapter<ResumoAtividadesAdapter.ResumoAtividadesHolder> {
    private final List<mQtdAtividadesResumo> listaResumo;
    private final Context context;

    public ResumoAtividadesAdapter(List<mQtdAtividadesResumo> listaResumo, Context context) {
        this.listaResumo = listaResumo;
        this.context = context;
    }

    @NonNull
    @Override
    public ResumoAtividadesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_qtd_atividades, parent, false);
        return new ResumoAtividadesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumoAtividadesHolder holder, int position) {
        mQtdAtividadesResumo resumo = this.listaResumo.get(position);

        String statusString = resumo.getQtdAtividades()+" "+resumo.getStatus();
        TextView status = holder.itemView.findViewById(R.id.des_qtd_atividades_stauts);
        ImageView iconStatus = holder.itemView.findViewById(R.id.icon_status_total);
        status.setText(statusString);

        if (resumo.getStatusId() == 0){
            iconStatus.setImageResource(R.drawable.not_disturb);
        } else if (resumo.getStatusId() == 1){
            iconStatus.setImageResource(R.drawable.check_circle);
        } else if (resumo.getStatusId() == 2) {
            iconStatus.setImageResource(R.drawable.cancel_icon);
        }
    }

    @Override
    public int getItemCount() {
        return this.listaResumo.size();
    }

    public static class ResumoAtividadesHolder extends RecyclerView.ViewHolder {
        public ResumoAtividadesHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

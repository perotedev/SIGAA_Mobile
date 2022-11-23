package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mQtdAtividadesResumo;

import java.util.List;

public class ResumoAtividadesAdapter extends ArrayAdapter<mQtdAtividadesResumo> {
    private final List<mQtdAtividadesResumo> listaResumo;
    private final Context context;

    public ResumoAtividadesAdapter(List<mQtdAtividadesResumo> listaResumo, Context context) {
        super(context, R.layout.card_item_qtd_atividades, listaResumo);
        this.listaResumo = listaResumo;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mQtdAtividadesResumo resumo = this.listaResumo.get(position);
        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(this.context).inflate(
                    R.layout.card_item_qtd_atividades,
                    parent,
                    false
            );
        }

        String statusString = resumo.getQtdAtividades()+" "+resumo.getStatus();
        TextView status = view.findViewById(R.id.des_qtd_atividades_stauts);
        ImageView iconStatus = view.findViewById(R.id.icon_status_total);
        status.setText(statusString);

        if (resumo.getStatusId() == 0){
            iconStatus.setImageResource(R.drawable.not_disturb);
        } else if (resumo.getStatusId() == 1){
            iconStatus.setImageResource(R.drawable.check_circle);
        } else {
            iconStatus.setImageResource(R.drawable.cancel_icon);
        }

        return view;
    }
}

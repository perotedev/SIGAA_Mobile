package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mAtividade;
import com.example.sigaamobile.ui.dialog.AtividadeExpiradaDialogFragment;
import com.example.sigaamobile.utils.DateTransform;
import com.google.gson.Gson;

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
        ImageView iconStatus = view.findViewById(R.id.icon_status);

        titulo.setText(atividade.getTitulo());
        descricao.setText(atividade.getDescricao());
        dataInicio.setText(DateTransform.transformToStringDate(atividade.getDataInicio(), DateTransform.SECONDS));
        dataFim.setText(DateTransform.transformToStringDate(atividade.getDataFim(), DateTransform.SECONDS));
        status.setText(atividade.getStatus());

        if (atividade.getStatusId() == 0){
            iconStatus.setImageResource(R.drawable.not_disturb);
        } else if (atividade.getStatusId() == 1){
            iconStatus.setImageResource(R.drawable.check_circle);
        } else if (atividade.getStatusId() == 2) {
            iconStatus.setImageResource(R.drawable.cancel_icon);
        }

        View finalView = view;
        view.setOnClickListener(v ->{
            if (atividade.getStatusId() == 1){
                String jsonString = new Gson().toJson(atividade);
                Bundle bundle = new Bundle();
                bundle.putString("atividade", jsonString);
                this.navigateTo(finalView, R.id.verRespostaAtividadeFragment, bundle);
            } else if (atividade.getStatusId() == 0){
                String jsonString = new Gson().toJson(atividade);
                Bundle bundle = new Bundle();
                bundle.putString("atividade", jsonString);
                this.navigateTo(finalView, R.id.verAtividadeFragment, bundle);
            } else {
                AtividadeExpiradaDialogFragment dialog = new AtividadeExpiradaDialogFragment();
                dialog.show(((MainActivity) context).getSupportFragmentManager(), "atividade");
            }
        });

        return view;
    }

    private void navigateTo(View actualView, int fragmentId, Bundle bundle){
        Navigation.findNavController(actualView).navigate(fragmentId, bundle);
    }
}

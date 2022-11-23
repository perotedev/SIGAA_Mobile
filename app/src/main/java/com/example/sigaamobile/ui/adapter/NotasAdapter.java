package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mNota;
import com.example.sigaamobile.models.mNotasDisciplina;
import java.util.ArrayList;
import java.util.List;

public class NotasAdapter extends RecyclerView.Adapter<NotasAdapter.NotasHolder> {
    private final List<mNotasDisciplina> notasArray;
    private final Context context;


    public NotasAdapter(List<mNotasDisciplina> notasArray, Context context){
        this.notasArray = notasArray;
        this.context = context;
    }

    @NonNull
    @Override
    public NotasHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_notas, parent, false);
        return new NotasHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotasAdapter.NotasHolder holder, int position) {
        mNotasDisciplina mNotasDisciplina = this.notasArray.get(position);
        ArrayList<mNota> notas = mNotasDisciplina.getNotas();

        TextView title_card_nota, content_nota1, content_nota2, content_nota3, content_rep, content_res;
        title_card_nota = holder.itemView.findViewById(R.id.title_card_nota);
        content_nota1 = holder.itemView.findViewById(R.id.content_nota1);
        content_nota2 = holder.itemView.findViewById(R.id.content_nota2);
        content_nota3 = holder.itemView.findViewById(R.id.content_nota3);
        content_rep = holder.itemView.findViewById(R.id.content_rep);
        content_res = holder.itemView.findViewById(R.id.content_res);

        title_card_nota.setText(mNotasDisciplina.getNomeDisciplina());
        this.setViewNota(content_nota1, this.getUndNota("und1", notas));
        this.setViewNota(content_nota2, this.getUndNota("und2", notas));
        this.setViewNota(content_nota3, this.getUndNota("und3", notas));
        this.setViewNota(content_rep, this.getUndNota("rep", notas));
        this.setViewNota(content_res, this.getUndNota("res", notas));
    }

    @Override
    public int getItemCount() {
        return this.notasArray.size();
    }

    public static class NotasHolder extends RecyclerView.ViewHolder {
        public NotasHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private void setViewNota(TextView textView, float nota){
        if (nota < 6 && nota != -1){
            textView.setTextColor(ContextCompat.getColor(context, R.color.bgError));
        }

        if (nota != -1){
            textView.setText(String.valueOf(nota));
        } else {
            textView.setText("-");
        }
    }

    private float getUndNota(String undNota, ArrayList<mNota> notas){
        float nota = 0;
        for (mNota mNota : notas){
            if (mNota.getUndNota().equals(undNota)){
                nota = mNota.getNota();
                break;
            }
        }
        return nota;
    }
}

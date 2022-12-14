package com.example.sigaamobile.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mDocumento;

import java.util.List;

public class DocumentosAdapter extends RecyclerView.Adapter<DocumentosAdapter.DocumentosHolder> {
    private final List<mDocumento> documentos;
    private final Context context;

    public DocumentosAdapter(List<mDocumento> documentos, Context context){
        this.documentos = documentos;
        this.context = context;
    }

    @NonNull
    @Override
    public DocumentosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_documento, parent, false);
        return new DocumentosHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentosHolder holder, int position) {
        mDocumento mDocumento = this.documentos.get(position);
        TextView title = holder.itemView.findViewById(R.id.title_doc);
        TextView descricao = holder.itemView.findViewById(R.id.desc_doc);

        title.setText(mDocumento.getNomeDocumento());
        descricao.setText(mDocumento.getDescricao());

        holder.itemView.setOnClickListener(v -> {
            String link = "https://www.caceres.mt.gov.br/fotos_institucional_downloads/2.pdf";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
            context.startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        return this.documentos.size();
    }

    public static class DocumentosHolder extends RecyclerView.ViewHolder {
        public DocumentosHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

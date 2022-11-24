package com.example.sigaamobile.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sigaamobile.R;

public class AtividadeExpiradaDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.atencao);
        builder.setMessage(R.string.atividade_expirada);
        builder.setNegativeButton(R.string.fechar, (dialog, which) -> dialog.cancel());
        return builder.create();
    }
}

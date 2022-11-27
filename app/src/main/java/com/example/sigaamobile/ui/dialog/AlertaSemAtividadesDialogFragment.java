package com.example.sigaamobile.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.sigaamobile.R;

public class AlertaSemAtividadesDialogFragment extends DialogFragment {
    String message;

    public AlertaSemAtividadesDialogFragment(String message){
        this.message = message;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.atencao);
        builder.setMessage(this.message);
        builder.setNegativeButton(R.string.fechar, (dialog, which) -> dialog.cancel());
        return builder.create();
    }
}

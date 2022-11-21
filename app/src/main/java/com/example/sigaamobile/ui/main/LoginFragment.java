package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.utils.JsonReader;

import org.json.JSONObject;

public class LoginFragment extends Fragment {
    private EditText username, password;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_sair);
        AppCompatButton btnEntrar = view.findViewById(R.id.btn_entrar);
        this.username = view.findViewById(R.id.edit_text_usuario);
        this.password = view.findViewById(R.id.edit_text_senha);

        btnEntrar.setOnClickListener(v -> {
            this.validarLogin(this.username.getText().toString(), this.password.getText().toString());
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.fragment_menu);
        });
    }
    private void validarLogin(String username, String password){
        JSONObject json = JsonReader.read("sampledata/user.json");
        System.out.println(json);
    }
}
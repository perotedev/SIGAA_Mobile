package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.os.Handler;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mUser;
import com.example.sigaamobile.utils.JsonReader;
import com.example.sigaamobile.utils.SigaaSharedPreferences;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

public class LoginFragment extends Fragment {
    private boolean showPassoword = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_sair);
        AppCompatButton btnEntrar = view.findViewById(R.id.btn_entrar);
        ImageView btnOlho = view.findViewById(R.id.icone_olho_senha);
        EditText username, password;
        username = view.findViewById(R.id.edit_text_usuario);
        password = view.findViewById(R.id.edit_text_senha);

        btnEntrar.setOnClickListener(v -> {
            boolean isUserValid = this.validarLogin(
                    username.getText().toString(),
                    password.getText().toString()
            );

            if (isUserValid){
                username.setText("");
                password.setText("");

                view.findViewById(R.id.login_loader).setVisibility(View.VISIBLE);
                this.animateAndNavigateToMenu();
            } else {
                Toast.makeText(
                        getContext(),
                        requireActivity().getString(R.string.invalid_login_alert),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        btnOlho.setOnClickListener(v -> {
            this.showPassoword = !this.showPassoword;
            System.out.println("inputType "+password.getInputType());
            if (this.showPassoword){
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
    }

    private boolean validarLogin(String username, String password){
        boolean isUserValid = false;
        JsonReader jsonReader = new JsonReader(requireActivity());
        JSONArray jsonArray = jsonReader.read("users", "user.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            mUser mUser = new mUser();

            try {
                Gson gson = new Gson();
                mUser = gson.fromJson(jsonArray.get(index).toString(), mUser.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            if (mUser.getUsername().equals(username) && mUser.getPassword().equals(password)){
                SigaaSharedPreferences preferences = new SigaaSharedPreferences(requireContext());
                preferences.setInt("userId", mUser.getUserId());

                isUserValid = true;
                break;
            }
        }
        return isUserValid;
    }

    private void animateAndNavigateToMenu(){
        ImageView logoSigaa = requireActivity().findViewById(R.id.logo_sigaa_loader);
        logoSigaa.animate().rotation(820f).setDuration(2200).start();

        // Não é válido para aplicação em uso real, estou apenas simulando o delay da consumo de API de login
        Handler handler = new Handler();
        handler.postDelayed(this::navigateToMenu, 1800);
    }

    private void navigateToMenu(){
        NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.fragment_menu);
        requireActivity().findViewById(R.id.login_loader).setVisibility(View.INVISIBLE);
    }
}
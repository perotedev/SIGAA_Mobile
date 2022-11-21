package com.example.sigaamobile.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sigaamobile.MainActivity;
import com.example.sigaamobile.R;
import com.example.sigaamobile.models.mUser;
import com.example.sigaamobile.utils.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity.setNavBarButton(requireActivity(), R.id.btn_sair);
        AppCompatButton btnEntrar = view.findViewById(R.id.btn_entrar);
        EditText username, password;
        username = view.findViewById(R.id.edit_text_usuario);
        password = view.findViewById(R.id.edit_text_senha);

        btnEntrar.setOnClickListener(v -> {
            boolean isUserValid = this.validarLogin(
                    username.getText().toString(),
                    password.getText().toString()
            );

            if (isUserValid){
                NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.fragment_menu);
            } else {
                Toast.makeText(
                        getContext(),
                        requireActivity().getString(R.string.invalid_login_alert),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private boolean validarLogin(String username, String password){
        boolean isUserValid = false;
        mUser mUser = new mUser();
        JsonReader jsonReader = new JsonReader(requireActivity());
        JSONArray jsonArray = jsonReader.read("users", "user.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            JSONObject object = null;
            try {
                object = (JSONObject) jsonArray.get(index);
                mUser.setUsername(object.getString("username"));
                mUser.setPassword(object.getString("password"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            assert object != null;

            if (mUser.getUsername().equals(username) && mUser.getPassword().equals(password)){
                isUserValid = true;
                break;
            }
        }
        return isUserValid;
    }
}
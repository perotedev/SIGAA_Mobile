package com.example.sigaamobile.utils;

import android.app.Activity;

import com.example.sigaamobile.models.mAluno;
import com.example.sigaamobile.models.mCurso;
import com.example.sigaamobile.models.mDadosAcademicos;
import com.example.sigaamobile.models.mNotasDisciplina;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class FromJson {
    public static mAluno getmAluno(int idUser, Activity activity) {
        mAluno mAluno = new mAluno();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("alunos", "aluno.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            try {
                Gson gson = new Gson();
                mAluno = gson.fromJson(jsonArray.get(index).toString(), mAluno.class);

                if (mAluno.getIdUser() == idUser){
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mAluno;
    }

    public static mDadosAcademicos getmDadosAcademicos(int idAluno, Activity activity) {
        mDadosAcademicos mDadosAcademicos = new mDadosAcademicos();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("dadosAcademicosAlunos", "dadosAcademicosAluno.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            try {
                Gson gson = new Gson();
                mDadosAcademicos = gson.fromJson(jsonArray.get(index).toString(), mDadosAcademicos.class);

                if (mDadosAcademicos.getIdAluno() == idAluno){
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mDadosAcademicos;
    }

    public static mCurso getmCurso(int idCurso, Activity activity) {
        mCurso mCurso = new mCurso();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("cursos", "curso.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            try {
                Gson gson = new Gson();
                mCurso = gson.fromJson(jsonArray.get(index).toString(), mCurso.class);

                if (mCurso.getIdCurso() == idCurso){
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mCurso;
    }

    public static ArrayList<mNotasDisciplina> getmNotasDisciplina(int idAluno, Activity activity) {
        ArrayList<mNotasDisciplina> arrayNotasDisciplinas = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("notasDisciplina", "notasDisciplina.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            mNotasDisciplina mNotasDisciplina;
            try {
                Gson gson = new Gson();
                mNotasDisciplina = gson.fromJson(jsonArray.get(index).toString(), mNotasDisciplina.class);
                if (mNotasDisciplina.getIdAluno() == idAluno){
                    arrayNotasDisciplinas.add(mNotasDisciplina);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arrayNotasDisciplinas;
    }
}

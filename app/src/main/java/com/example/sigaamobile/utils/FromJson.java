package com.example.sigaamobile.utils;

import android.app.Activity;

import com.example.sigaamobile.models.mAluno;
import com.example.sigaamobile.models.mAtividadesDisciplina;
import com.example.sigaamobile.models.mCurso;
import com.example.sigaamobile.models.mDadosAcademicos;
import com.example.sigaamobile.models.mDocumento;
import com.example.sigaamobile.models.mFrequenciaTodasAulas;
import com.example.sigaamobile.models.mNotasDisciplina;
import com.example.sigaamobile.models.mRespostaAtividade;
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

    public static ArrayList<mDocumento> getmDocumentos(Activity activity) {
        ArrayList<mDocumento> arraymDocumentos = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("documentos", "documentos.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            mDocumento mDocumento;
            try {
                Gson gson = new Gson();
                mDocumento = gson.fromJson(jsonArray.get(index).toString(), mDocumento.class);
                arraymDocumentos.add(mDocumento);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arraymDocumentos;
    }

    public static ArrayList<mFrequenciaTodasAulas> getmFrequenciaTodasAulas(int idAluno, Activity activity) {
        ArrayList<mFrequenciaTodasAulas> arraymFrequenciaTodasAulas = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("frequencias", "frequenciaDisciplina.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            mFrequenciaTodasAulas mFrequenciaTodasAulas;
            try {
                Gson gson = new Gson();
                mFrequenciaTodasAulas = gson.fromJson(jsonArray.get(index).toString(), mFrequenciaTodasAulas.class);
                if (mFrequenciaTodasAulas.getIdAluno() == idAluno){
                    arraymFrequenciaTodasAulas.add(mFrequenciaTodasAulas);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return arraymFrequenciaTodasAulas;
    }

    public static ArrayList<mAtividadesDisciplina> getmAtividadesDisciplina(int idAluno, Activity activity) {
        ArrayList<mAtividadesDisciplina> atividadesArray = new ArrayList<>();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("atividades", "atividades.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            mAtividadesDisciplina mAtividadesDisciplina;
            try {
                Gson gson = new Gson();
                mAtividadesDisciplina = gson.fromJson(jsonArray.get(index).toString(), mAtividadesDisciplina.class);
                if (mAtividadesDisciplina.getIdAluno() == idAluno){
                    atividadesArray.add(mAtividadesDisciplina);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return atividadesArray;
    }

    public static mRespostaAtividade getmRespostaAtividade(int idAtividade, int idAluno, Activity activity) {
        mRespostaAtividade mRespostaAtividade = new mRespostaAtividade();
        JsonReader jsonReader = new JsonReader(activity);
        JSONArray jsonArray = jsonReader.read("respostas", "respostaAtividade.json");

        for (int index = 0; index < jsonArray.length(); index ++){
            try {
                Gson gson = new Gson();
                mRespostaAtividade resposta = gson.fromJson(jsonArray.get(index).toString(), mRespostaAtividade.class);
                if (resposta.getAtividadeId() == idAtividade && resposta.getIdAluno() == idAluno){
                    mRespostaAtividade = resposta;
                    break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mRespostaAtividade;
    }
}

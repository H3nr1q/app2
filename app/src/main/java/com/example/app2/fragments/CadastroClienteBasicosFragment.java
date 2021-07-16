package com.example.app2.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app2.R;
import com.example.app2.utils.ValidaEmail;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

public class CadastroClienteBasicosFragment extends CadastroClienteFragment {

    static TextInputLayout nome, nomeFantasia, emailPrinc, emailSecun;
    static ValidaEmail validaEmail;

    public static CadastroClienteBasicosFragment newInstance(){
        CadastroClienteBasicosFragment fragment = new CadastroClienteBasicosFragment();

        return fragment;
    }

    public CadastroClienteBasicosFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }

        verificaInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cadastro_cliente_basicos,
                container, false);

        nome = view.findViewById(R.id.inputNome);
        nomeFantasia = view.findViewById(R.id.inputNomeFantasia);
        emailPrinc = view.findViewById(R.id.inputEmailPrin);
        emailSecun = view.findViewById(R.id.inputEmailSecun);

        validaEmail = new ValidaEmail();

        if (getCliente() != null){
            if (getCliente().getRazao_social() != null) {
                if (nome.getEditText().getText() == null){
                    nome.getEditText().setText(getCliente().getRazao_social());
                }
            }
            if (getCliente().getNome_fantasia() != null){
                if (nomeFantasia.getEditText().getText() == null){
                    nomeFantasia.getEditText().setText(getCliente().getNome_fantasia());
                }
            }
            if (getCliente().getEmail_pricipal() != null){
                if (emailPrinc.getEditText().getText() == null){
                    emailPrinc.getEditText().setText(getCliente().getEmail_pricipal().trim());
                }
            }
            if (getCliente().getEmail_secundario() != null){
                if (emailSecun.getEditText().getText() == null){
                    emailSecun.getEditText().setText(getCliente().getEmail_secundario().trim());
                }
            }
            nome.getEditText().setText(getCliente().getRazao_social());
            nomeFantasia.getEditText().setText(getCliente().getNome_fantasia());
            emailPrinc.getEditText().setText(getCliente().getEmail_pricipal());
            emailSecun.getEditText().setText(getCliente().getEmail_secundario());
        }

        if (getActivity() != null){
            if (getCliente() != null){
                if (getActivity().getIntent().getStringExtra("cpfoucnpj") != null){


                    getCliente().setCpf(getActivity().getIntent().getStringExtra("cpfoucnpj"));
                    String tipo = getActivity().getIntent().getStringExtra("tipoCliente");

                    switch ( tipo ){
                        case "Jurídico":
                            nome.setHint("Razão Social");
                            nomeFantasia.setVisibility(View.VISIBLE);
                            break;

                        case "Física":
                            nomeFantasia.setVisibility(View.GONE);
                    }

                } else {
                    if (getCliente().getNome_fantasia() != null){
                        if (!getCliente().getNome_fantasia().isEmpty()){
                            nomeFantasia.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }

        verificaInstanceState(savedInstanceState);

        return view;
    }

    @Override
    public boolean isValid() {

        boolean isValido = true;

        if (nome != null){
            if (TextUtils.isEmpty(nome.getEditText().getText().toString())){
                nome.setError("Campo Obrigatório");
                isValido = false;
            } else if (nome.getError()!=null){
                nome.setError(null);
            }
            if (getCliente()!=null){
                getCliente().setRazao_social(nome.getEditText().getText().toString());
            }
        }

        if (nomeFantasia != null && nomeFantasia.getVisibility() == View.VISIBLE){
            if (TextUtils.isEmpty(nomeFantasia.getEditText().getText().toString())){
                nomeFantasia.setError("Campo Obrigatório");
                isValido = false;
            } else if (nomeFantasia.getError()!=null){
                nomeFantasia.setError(null);
            }
            if (getCliente()!=null){
                getCliente().setNome_fantasia(nomeFantasia.getEditText().getText().toString());
            }
        }

        if (emailPrinc != null){
            if (TextUtils.isEmpty(emailPrinc.getEditText().getText().toString())){
                emailPrinc.setError("Campo Obrigatório");
                isValido = false;
            } else if (validaEmail.validaEmail(
                    emailPrinc.getEditText().getText().toString().trim()) == false) {
                emailPrinc.setError("Formato do email incorreto");
                isValido = false;
            } else if (emailPrinc.getError()!=null){
                emailPrinc.setError(null);
            }
            if (getCliente()!=null){
                getCliente().setEmail_pricipal(emailPrinc.getEditText().getText().toString());
            }
        }


        if (emailSecun != null){
            if (!TextUtils.isEmpty(emailSecun.getEditText().getText().toString())){
                if (validaEmail.validaEmail(
                        emailSecun.getEditText().getText().toString().trim()) == false) {
                    emailSecun.setError("Formato do email incorreto");
                    isValido = false;
                } else if (emailSecun.getError()!=null){
                    emailSecun.setError(null);
                }
                if (getCliente()!=null){
                    getCliente().setEmail_secundario(emailSecun.getEditText().getText().toString());
                }
            }
        }


        if (getCliente() != null){
            if (getCliente().getTipo_pessoa() == null){
                if (getActivity().getIntent().getStringExtra("tipoCliente").equals("Jurídico")){
                    getCliente().setTipo_pessoa("J");
                } else {
                    getCliente().setTipo_pessoa("F");
                }
            }
        }


        return isValido;

    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!TextUtils.isEmpty(nome.getEditText().getText().toString())){
            outState.putString("nome", nome.getEditText().getText().toString());
        }
        if (!TextUtils.isEmpty(nomeFantasia.getEditText().getText().toString())){
            outState.putString("nomeFantasia", nomeFantasia.getEditText().getText().toString());
        }
        if (!TextUtils.isEmpty(emailPrinc.getEditText().getText().toString())){
            outState.putString("emailPrinc", emailPrinc.getEditText().getText().toString());
        }
        if (!TextUtils.isEmpty(emailSecun.getEditText().getText().toString())){
            outState.putString("emailSec", emailSecun.getEditText().getText().toString());
        }
    }

    private void verificaInstanceState(Bundle savedInstanceState){

        if (savedInstanceState != null){
            if (savedInstanceState.getString("nome") != null ){
                nome.getEditText().setText(savedInstanceState.getString("nome"));
            }
            if (savedInstanceState.getString("nomeFantasia") != null){
                nomeFantasia.getEditText().setText(savedInstanceState.getString("nomeFantasia"));
            }
            if (savedInstanceState.getString("emailPrinc") != null){
                emailPrinc.getEditText().setText(savedInstanceState.getString("emailPrinc"));
            }
            if (savedInstanceState.getString("emailSec") != null){
                emailSecun.getEditText().setText(savedInstanceState.getString("emailSec"));
            }
        }

    }

}
package com.example.app2.fragments;

import android.os.Bundle;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;

import com.example.app2.R;
import com.example.app2.activities.CadastrarClientePresenter;
import com.example.app2.models.Endereco;
import com.example.app2.models.Municipio;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CadastroEnderecosClientesFragment extends CadastroClienteFragment
        implements CadastrarClientePresenter.CadastrarClienteView{

    private View view;
    private TextInputLayout inputEndereco, inputNumero, inputComplemento, inputBairro,
            inputCodMunicipio, inputCep, inputEstado, inputMunicipio; //Endereço principal
    private TextInputLayout inputEndereco1, inputNumero1, inputComplemento1, inputBairro1,
            inputEstadoAdc1, inputMunicipioAdc1; //Endereço adicional 1
    private TextInputLayout inputEndereco2, inputNumero2, inputComplemento2,
            inputBairro2, inputEstadoAdc2, inputMunicipioAdc2; //Endereço adicional 2
    private AutoCompleteTextView listEstados1, listEstados2, listEstados3,
            listMunicipios1, listMunicipios2, listMunicipios3;
    static ArrayAdapter<CharSequence> adapter1, adapter2, adapter3;
    static List<String> municipioList1, municipioList2, municipioList3;
    static int numberSpinner;
    static String cod_Municipio, cod_Municipio1, cod_Municipio2,
            mEstado1, mEstado2, mEstado3, mMunicipio1, mMunicipio2, mMunicipio3;
    static CadastrarClientePresenter cadastrarClientePresenter;
    static Municipio mMunicipio;
    static Endereco mEnderecoAdc, mEnderecoAdc1;
    static List<Endereco> enderecos;
    static ArrayAdapter<CharSequence> adapter;

    public static CadastroEnderecosClientesFragment newInstance(){
        CadastroEnderecosClientesFragment fragment = new CadastroEnderecosClientesFragment();
        return fragment;
    }

    public CadastroEnderecosClientesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cadastro_enderecos_clientes, container, false);

        cadastrarClientePresenter = new CadastrarClientePresenter();
        cadastrarClientePresenter.setmCadastrarClienteView(this);

        bind();

        listEstados1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                inputEstado.setHint("Estado");
                inputMunicipio.setEnabled(true);
                inputMunicipio.setHint("Municipio");
                numberSpinner = 1;
                String teste = s.toString();
                cadastrarClientePresenter.buscarCidades(teste);
                mEstado1 = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {

                inputEstado.setHint("Estado");
                inputMunicipio.setEnabled(true);
                inputMunicipio.setHint("Municipio");
                numberSpinner = 1;
                cadastrarClientePresenter.buscarCidades(s.toString());
                mEstado1 = s.toString();
            }
        });

        listEstados2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputEstadoAdc1.setHint("Estado");
                inputMunicipioAdc1.setEnabled(true);
                inputMunicipioAdc1.setHint("Municipio");
                numberSpinner = 2;
                String teste = s.toString();
                cadastrarClientePresenter.buscarCidades(teste);
                mEstado2 = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputEstadoAdc1.setHint("Estado");
                inputMunicipioAdc1.setEnabled(true);
                inputMunicipioAdc1.setHint("Municipio");
                numberSpinner = 2;
                cadastrarClientePresenter.buscarCidades(s.toString());
                mEstado2 = s.toString();
            }
        });

        listEstados3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputEstadoAdc2.setHint("Estado");
                inputMunicipioAdc2.setEnabled(true);
                inputMunicipioAdc2.setHint("Municipio");
                numberSpinner = 3;
                String teste = s.toString();
                cadastrarClientePresenter.buscarCidades(teste);
                mEstado3 = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
                inputEstadoAdc2.setHint("Estado");
                inputMunicipioAdc2.setEnabled(true);
                inputMunicipioAdc2.setHint("Municipio");
                numberSpinner = 3;
                cadastrarClientePresenter.buscarCidades(s.toString());
                mEstado3 = s.toString();
            }
        });

        if (listMunicipios1 != null){
            listMunicipios1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mMunicipio1 = s.toString();
                    cod_Municipio = cadastrarClientePresenter.buscarCodMunicipio(s.toString());

                }

                @Override
                public void afterTextChanged(Editable s) {
                    cod_Municipio = cadastrarClientePresenter.buscarCodMunicipio(s.toString());
                    mMunicipio1 = s.toString();
                    if (getCliente() != null){
                        getCliente().setCodigo_municipio(cod_Municipio);
                    }
                }
            });
        }

        if (listMunicipios2 != null){
            listMunicipios2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    cod_Municipio1 = cadastrarClientePresenter.buscarCodMunicipio(s.toString());
                    mMunicipio2 = s.toString();
                    mEnderecoAdc.setCodigoMunicipio(cod_Municipio1);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    cod_Municipio1 = cadastrarClientePresenter.buscarCodMunicipio(s.toString());
                    mMunicipio2 = s.toString();
                    mEnderecoAdc.setCodigoMunicipio(cod_Municipio1);
                }
            });
        }

        if (listMunicipios3 != null){
            listMunicipios3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    cod_Municipio2 = cadastrarClientePresenter.buscarCodMunicipio(s.toString());
                    mMunicipio3 = s.toString();
                    mEnderecoAdc1.setCodigoMunicipio(cod_Municipio2);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    cod_Municipio2 = cadastrarClientePresenter.buscarCodMunicipio(s.toString());
                    mMunicipio3 = s.toString();
                    mEnderecoAdc1.setCodigoMunicipio(cod_Municipio2);
                }
            });
        }

        if (getCliente() != null){

            cadastrarClientePresenter.burcarEnderecos(getCliente().getCodigo_cliente());

            if (getCliente().getEndereco() != null){
                if (inputEndereco.getEditText().getText().toString().equals("")){
                    inputEndereco.getEditText().setText(getCliente().getEndereco());
                }
            }

            if (getCliente().getNumero() != null){
                if (inputNumero.getEditText().getText().toString().equals("")){
                    inputNumero.getEditText().setText(getCliente().getNumero());
                }
            }

            if (getCliente().getComplemento() != null){
                if (inputComplemento.getEditText().getText().toString().equals("")){
                    inputComplemento.getEditText().setText(getCliente().getComplemento());
                }
            }

            if (getCliente().getBairro() != null){
                if (inputBairro.getEditText().getText().toString().equals(""))
                    inputBairro.getEditText().setText(getCliente().getBairro());
            }

            if (getCliente().getCodigo_municipio() != null){

                cadastrarClientePresenter.buscarCidadeEstado(getCliente().getCodigo_municipio());

                inputEstado.setHint(mMunicipio.getEstado());
                //listMunicipios1.setText(mMunicipio.getCidade());
                inputMunicipio.setHint(mMunicipio.getCidade());
                inputMunicipio.setEnabled(false);

            }

            if (getCliente().getCEP() != null){
                if (inputCep.getEditText().getText().toString().equals("")){
                    inputCep.getEditText().setText(getCliente().getCEP());
                }
            }

            if (enderecos != null){

                if (enderecos.size() == 1){

                    inputEndereco1.getEditText().setText(enderecos.get(0).getEndereco());
                    inputNumero1.getEditText().setText(enderecos.get(0).getNumero());
                    inputComplemento1.getEditText().setText(enderecos.get(0).getComplememnto());
                    inputBairro1.getEditText().setText(enderecos.get(0).getBairro());

                    if (enderecos.get(0).getCodigoMunicipio() != null){

                        cadastrarClientePresenter.buscarCidadeEstado(
                                enderecos.get(0).getCodigoMunicipio());

                        inputEstadoAdc1.setHint(mMunicipio.getEstado());
                        inputMunicipioAdc1.setHint(mMunicipio.getCidade());
                        inputMunicipioAdc1.setEnabled(false);

                    }

                } else if (enderecos.size() == 2){

                    inputEndereco1.getEditText().setText(enderecos.get(0).getEndereco());
                    inputNumero1.getEditText().setText(enderecos.get(0).getNumero());
                    inputComplemento1.getEditText().setText(enderecos.get(0).getComplememnto());
                    inputBairro1.getEditText().setText(enderecos.get(0).getBairro());

                    if (enderecos.get(0).getCodigoMunicipio() != null){

                        cadastrarClientePresenter.buscarCidadeEstado(
                                enderecos.get(0).getCodigoMunicipio());
                        inputEstadoAdc1.setHint(mMunicipio.getEstado());
                        inputMunicipioAdc1.setHint(mMunicipio.getCidade());
                        inputMunicipioAdc1.setEnabled(false);

                    }

                    inputEndereco2.getEditText().setText(enderecos.get(1).getEndereco());
                    inputNumero2.getEditText().setText(enderecos.get(1).getNumero());
                    inputComplemento2.getEditText().setText(enderecos.get(1).getComplememnto());
                    inputBairro2.getEditText().setText(enderecos.get(1).getBairro());

                    if (enderecos.get(1).getCodigoMunicipio() != null){

                        cadastrarClientePresenter.buscarCidadeEstado(
                                enderecos.get(1).getCodigoMunicipio());
                        inputEstadoAdc2.setHint(mMunicipio.getEstado());
                        inputMunicipioAdc2.setHint(mMunicipio.getCidade());
                        inputMunicipioAdc2.setEnabled(false);

                    }

                }

            }

        }

        verificaInstance(savedInstanceState);

        return view;
    }

    public void bind(){


        inputEndereco = view.findViewById(R.id.inputEndereco);
        inputNumero = view.findViewById(R.id.inputNumero);
        inputComplemento = view.findViewById(R.id.inputComplemento);
        inputBairro = view.findViewById(R.id.inputBairro);
        inputCep = view.findViewById(R.id.inputCEP);
        listEstados1 = view.findViewById(R.id.acEstado1);
        listMunicipios1 = view.findViewById(R.id.acMunicipio1);
        inputEstado = view.findViewById(R.id.inputEstado);
        inputMunicipio = view.findViewById(R.id.inputMunicipio);

        inputEndereco1 = view.findViewById(R.id.inputEnderecoAdc1);
        inputNumero1 = view.findViewById(R.id.inputNumeroAdc1);
        inputComplemento1 = view.findViewById(R.id.inputComplementoAdc1);
        inputBairro1 = view.findViewById(R.id.inputBairroAdc1);
        listEstados2 = view.findViewById(R.id.acEstado2);
        listMunicipios2 = view.findViewById(R.id.acMunicipio2);
        inputEstadoAdc1 = view.findViewById(R.id.inputEstado1);
        inputMunicipioAdc1 = view.findViewById(R.id.inputMunicipio1);

        inputEndereco2 = view.findViewById(R.id.inputEnderecoAdc2);
        inputNumero2 = view.findViewById(R.id.inputNumeroAdc2);
        inputComplemento2 = view.findViewById(R.id.inputComplementoAdc2);
        inputBairro2 = view.findViewById(R.id.inputBairroAdc2);
        listEstados3 = view.findViewById(R.id.acEstado3);
        listMunicipios3 = view.findViewById(R.id.acMunicipio3);
        inputEstadoAdc2 = view.findViewById(R.id.inputEstado2);
        inputMunicipioAdc2 = view.findViewById(R.id.inputMunicipio2);


        adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.listEstados, android.R.layout.simple_list_item_1);
        listEstados1.setAdapter(adapter);
        listEstados2.setAdapter(adapter);
        listEstados3.setAdapter(adapter);

        mEnderecoAdc = new Endereco();
        mEnderecoAdc1 = new Endereco();

        mMunicipio1 = "";
        mMunicipio2 = "";
        mMunicipio3 = "";

    }

    @Override
    public boolean isValid() {

        boolean isvalido = true;

        if (inputEndereco != null){
            if (TextUtils.isEmpty(inputEndereco.getEditText().getText().toString())){
                inputEndereco.setError("Campo Obrigatório");
                isvalido = false;
            } else if (inputEndereco.getError()!=null){
                inputEndereco.setError(null);
            }
            if (getCliente()!=null){
                getCliente().setEndereco(inputEndereco.getEditText().getText().toString());
            }
        }

        if (inputNumero != null){
            if (TextUtils.isEmpty(inputNumero.getEditText().getText().toString())){
                inputNumero.setError("Campo Obrigatório");
                isvalido = false;
            } else if (inputNumero.getError()!=null){
                inputNumero.setError(null);
            }
            if (getCliente()!=null){
                getCliente().setNumero(inputNumero.getEditText().getText().toString());
            }
        }

        if (inputBairro != null){
            if (TextUtils.isEmpty(inputBairro.getEditText().getText().toString())){
                inputBairro.setError("Campo Obrigatório");
                isvalido = false;
            } else if (inputBairro.getError()!=null) {
                inputBairro.setError(null);
            }
            if (getCliente() != null){
                getCliente().setBairro(inputBairro.getEditText().getText().toString());
            }
        }

        if (inputCep != null){
            if (TextUtils.isEmpty(inputCep.getEditText().getText().toString())){
                inputCep.setError("Campo Obrigatório");
                isvalido = false;
            } else if (inputCep.getError()!=null){
                inputCep.setError(null);
            }
            if (getCliente() != null){
                getCliente().setCEP(inputCep.getEditText().getText().toString());
            }
        }

        if (mEstado1 != null){
            if (mMunicipio1 == null || mMunicipio1.equals("")){
                inputMunicipio.setError("Campo Obrigatório");
                isvalido = false;
            } else if (inputMunicipio.getError()!=null){
                inputMunicipio.setError(null);
            }
        }


        //Dados não obrigatórios

        if (inputComplemento != null) {
            if (!TextUtils.isEmpty(inputComplemento.getEditText().getText().toString())){
                if (getCliente()!=null){
                    getCliente().setComplemento(inputComplemento.getEditText().getText().toString());
                }
            }
        }

        if (inputCodMunicipio != null) {
            if (!TextUtils.isEmpty(inputCodMunicipio.getEditText().getText().toString())){
                if (getCliente()!=null){
                    getCliente().setCodigo_municipio(
                            inputCodMunicipio.getEditText().getText().toString());
                }
            }
        }

        if (inputEndereco1 != null){
            if (!TextUtils.isEmpty(inputEndereco1.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc.setEndereco(inputEndereco1.getEditText().getText().toString());
                }
            }
        }

        if (inputNumero1 != null){
            if (!TextUtils.isEmpty(inputNumero1.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc.setNumero(inputNumero1.getEditText().getText().toString());
                }
            }
        }

        if (inputComplemento1 != null){
            if (!TextUtils.isEmpty(inputComplemento1.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc.setComplememnto(
                            inputComplemento1.getEditText().getText().toString());
                }
            }
        }

        if (inputBairro1 != null){
            if (!TextUtils.isEmpty(inputBairro1.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc.setBairro(inputBairro1.getEditText().getText().toString());
                    mEnderecoAdc.setCodigoCliente(getCliente().getCodigo_cliente());
                }
            }
        }

        if (enderecos.size() != 0){
            if (mEnderecoAdc.getEndereco() != null || !mEnderecoAdc.getEndereco().equals("")){
                if (enderecos.get(0).getCodigoEndereco() != 0){
                    mEnderecoAdc.setCodigoEndereco(enderecos.get(0).getCodigoEndereco());
                }
                if (mEnderecoAdc.getCodigoMunicipio() == null){
                    mEnderecoAdc.setCodigoMunicipio(enderecos.get(0).getCodigoMunicipio());
                }
                getCliente().getEnderecosAdc().add(mEnderecoAdc);
            }
        } else{
            if (mEnderecoAdc.getEndereco() != null || !mEnderecoAdc.getEndereco().equals("")){
                Random gerador = new Random();
                mEnderecoAdc.setCodigoEndereco(gerador.nextInt(11));
                getCliente().getEnderecosAdc().add(mEnderecoAdc);
            }
        }

        if (inputEndereco2 != null){
            if (!TextUtils.isEmpty(inputEndereco2.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc1.setEndereco(inputEndereco2.getEditText().getText().toString());
                }
            }
        }

        if (inputNumero2 != null){
            if (!TextUtils.isEmpty(inputNumero2.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc1.setNumero(inputNumero2.getEditText().getText().toString());
                }
            }
        }

        if (inputComplemento2 != null){
            if (!TextUtils.isEmpty(inputComplemento2.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc1.setComplememnto(
                            inputComplemento2.getEditText().getText().toString());
                }
            }
        }

        if (inputBairro2 != null){
            if (!TextUtils.isEmpty(inputBairro2.getEditText().getText().toString())){
                if (getCliente()!=null){
                    mEnderecoAdc1.setBairro(inputBairro2.getEditText().getText().toString());
                    mEnderecoAdc1.setCodigoCliente(getCliente().getCodigo_cliente());
                }
            }
        }

        if (mEnderecoAdc1 != null){
            if (enderecos.size() > 1){
                if (mEnderecoAdc1.getEndereco() != null ){
                    if (!mEnderecoAdc1.getEndereco().equals("")){
                        if (enderecos.get(1).getCodigoEndereco() != 0){
                            mEnderecoAdc1.setCodigoEndereco(enderecos.get(1).getCodigoEndereco());
                        }
                        if (mEnderecoAdc1.getCodigoMunicipio() == null){
                            mEnderecoAdc1.setCodigoMunicipio(enderecos.get(1).getCodigoMunicipio());
                        }
                        getCliente().getEnderecosAdc().add(mEnderecoAdc1);
                    }
                }
            } else{
                if (mEnderecoAdc1.getEndereco() != null ){
                    if (!mEnderecoAdc1.getEndereco().equals("")){
                        Random gerador = new Random();
                        mEnderecoAdc1.setCodigoEndereco(gerador.nextInt(11));
                        getCliente().getEnderecosAdc().add(mEnderecoAdc1);
                    }
                }
            }
        }
        return isvalido;
    }

    @Override
    public void ListarMunicipio(List<String> municipios) {

        if (numberSpinner == 1){
            municipioList1 = new ArrayList<>();
            municipioList1 = municipios;
            adapter1 = new ArrayAdapter(getContext(),
                    android.R.layout.simple_list_item_1, municipioList1);
            listMunicipios1.setAdapter(adapter1);
        } if (numberSpinner == 2){
            municipioList2 = new ArrayList<>();
            municipioList2 = municipios;
            adapter2 = new ArrayAdapter(getContext(),
                    android.R.layout.simple_list_item_1, municipioList2);
            listMunicipios2.setAdapter(adapter2);
        } if (numberSpinner == 3){
            municipioList3 = new ArrayList<>();
            municipioList3 = municipios;
            adapter3 = new ArrayAdapter(getContext(),
                    android.R.layout.simple_list_item_1, municipioList3);
            listMunicipios3.setAdapter(adapter3);
        }

    }

    @Override
    public void setMunicipio(Municipio municipio) {
        mMunicipio = municipio;
    }

    @Override
    public void setEnderecos(List<Endereco> endereco) {
        enderecos = new ArrayList<>();
        enderecos = endereco;
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!TextUtils.isEmpty(inputEndereco.getEditText().getText().toString())){
            outState.putString("endereco1", inputEndereco.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputNumero.getEditText().getText().toString())){
            outState.putString("numero1", inputNumero.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputComplemento.getEditText().getText().toString())){
            outState.putString("complemento1", inputComplemento.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputBairro.getEditText().getText().toString())){
            outState.putString("bairro1", inputBairro.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputCep.getEditText().getText().toString())){
            outState.putString("cep", inputCep.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputEndereco1.getEditText().getText().toString())){
            outState.putString("endereco2", inputEndereco1.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputNumero1.getEditText().getText().toString())){
            outState.putString("numero2", inputNumero.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputComplemento1.getEditText().getText().toString())){
            outState.putString("complemento2", inputComplemento.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputBairro1.getEditText().getText().toString())){
            outState.putString("bairro2", inputBairro.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputEndereco1.getEditText().getText().toString())){
            outState.putString("endereco3", inputEndereco1.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputNumero1.getEditText().getText().toString())){
            outState.putString("numero3", inputNumero.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputComplemento1.getEditText().getText().toString())){
            outState.putString("complemento3", inputComplemento.getEditText().getText().toString());
        }

        if (!TextUtils.isEmpty(inputBairro1.getEditText().getText().toString())){
            outState.putString("bairro3", inputBairro.getEditText().getText().toString());
        }

    }

    private void verificaInstance(Bundle savedInstanceState){

        if (savedInstanceState != null){
            if (savedInstanceState.getString("endereco1") != null){
                inputEndereco.getEditText().setText(savedInstanceState.getString("endereco1"));
            }

            if (savedInstanceState.getString("numero1") != null){
                inputNumero.getEditText().setText(savedInstanceState.getString("numero1"));
            }

            if (savedInstanceState.getString("complemento1") != null){
                inputComplemento.getEditText().setText(
                        savedInstanceState.getString("complemento1"));
            }

            if (savedInstanceState.getString("bairro1") != null){
                inputBairro.getEditText().setText(savedInstanceState.getString("bairro1"));
            }

            if (savedInstanceState.getString("cep") != null ){
                inputCep.getEditText().setText(savedInstanceState.getString("cep"));
            }

            if (savedInstanceState.getString("endereco2") != null){
                inputEndereco1.getEditText().setText(savedInstanceState.getString("endereco2"));
            }

            if (savedInstanceState.getString("numero2") != null){
                inputNumero1.getEditText().setText(savedInstanceState.getString("numero2"));
            }

            if (savedInstanceState.getString("complemento2") != null){
                inputComplemento1.getEditText().setText(
                        savedInstanceState.getString("complemento2"));
            }

            if (savedInstanceState.getString("bairro2") != null){
                inputBairro1.getEditText().setText(savedInstanceState.getString("bairro2"));
            }

            if (savedInstanceState.getString("endereco3") != null){
                inputEndereco2.getEditText().setText(savedInstanceState.getString("endereco3"));
            }

            if (savedInstanceState.getString("numero3") != null){
                inputNumero2.getEditText().setText(savedInstanceState.getString("numero3"));
            }

            if (savedInstanceState.getString("complemento3") != null){
                inputComplemento2.getEditText().setText(
                        savedInstanceState.getString("complemento3"));
            }

            if (savedInstanceState.getString("bairro3") != null){
                inputBairro2.getEditText().setText(savedInstanceState.getString("bairro3"));
            }


        }

    }

}
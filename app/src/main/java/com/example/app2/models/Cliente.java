package com.example.app2.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Cliente implements Parcelable {

    private String codigo_cliente;
    private String razao_social; //nome
    private String cpf; //Ou CNPJ
    private String nome_fantasia;
    private String email_pricipal;
    private String email_secundario;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String codigo_municipio;
    private String CEP;
    private String tipo_pessoa;
    private List<Endereco> enderecosAdc = new ArrayList<>();

    public Cliente(){

    }

    public Cliente(String codigo_cliente, String razao_social, String cpf, String nome_fantasia,
                   String email_pricipal, String email_secundario, String endereco, String numero,
                   String complemento, String bairro, String codigo_municipio, String CEP,
                   String tipo_pessoa, List<Endereco> enderecosAdc) {
        this.codigo_cliente = codigo_cliente;
        this.razao_social = razao_social;
        this.cpf = cpf;
        this.nome_fantasia = nome_fantasia;
        this.email_pricipal = email_pricipal;
        this.email_secundario = email_secundario;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.codigo_municipio = codigo_municipio;
        this.CEP = CEP;
        this.tipo_pessoa = tipo_pessoa;
        this.enderecosAdc = enderecosAdc;
    }


    protected Cliente(Parcel in) {
        codigo_cliente = in.readString();
        razao_social = in.readString();
        cpf = in.readString();
        nome_fantasia = in.readString();
        email_pricipal = in.readString();
        email_secundario = in.readString();
        endereco = in.readString();
        numero = in.readString();
        complemento = in.readString();
        bairro = in.readString();
        codigo_municipio = in.readString();
        CEP = in.readString();
        tipo_pessoa = in.readString();
        enderecosAdc = in.readArrayList(null);
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel in) {
            return new Cliente(in);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome_fantasia() {
        return nome_fantasia;
    }

    public void setNome_fantasia(String nome_fantasia) {
        this.nome_fantasia = nome_fantasia;
    }

    public String getEmail_pricipal() {
        return email_pricipal;
    }

    public void setEmail_pricipal(String email_pricipal) {
        this.email_pricipal = email_pricipal;
    }

    public String getEmail_secundario() {
        return email_secundario;
    }

    public void setEmail_secundario(String email_secundario) {
        this.email_secundario = email_secundario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(String codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getTipo_pessoa() {
        return tipo_pessoa;
    }

    public void setTipo_pessoa(String tipo_pessoa) {
        this.tipo_pessoa = tipo_pessoa;
    }

    public List<Endereco> getEnderecosAdc() {
        return enderecosAdc;
    }

    public void setEnderecosAdc(List<Endereco> enderecosAdc) {
        this.enderecosAdc = enderecosAdc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo_cliente);
        dest.writeString(razao_social);
        dest.writeString(cpf);
        dest.writeString(nome_fantasia);
        dest.writeString(email_pricipal);
        dest.writeString(email_secundario);
        dest.writeString(endereco);
        dest.writeString(numero);
        dest.writeString(complemento);
        dest.writeString(bairro);
        dest.writeString(codigo_municipio);
        dest.writeString(CEP);
        dest.writeString(tipo_pessoa);
        dest.writeList(enderecosAdc);
    }
}

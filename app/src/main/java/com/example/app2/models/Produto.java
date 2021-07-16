package com.example.app2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Produto implements Parcelable {

    private String codigo;
    private String descricao;
    private String status;
    private String estoque;
    private String precoMax;
    private String precoMin;

    public Produto(){

    }

    public Produto(String codigo, String descricao, String status, String estoque, String precoMax, String precoMin) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.status = status;
        this.estoque = estoque;
        this.precoMax = precoMax;
        this.precoMin = precoMin;
    }

    protected Produto(Parcel in) {
        codigo = in.readString();
        descricao = in.readString();
        status = in.readString();
        estoque = in.readString();
        precoMax = in.readString();
        precoMin = in.readString();
    }

    public static final Creator<Produto> CREATOR = new Creator<Produto>() {
        @Override
        public Produto createFromParcel(Parcel in) {
            return new Produto(in);
        }

        @Override
        public Produto[] newArray(int size) {
            return new Produto[size];
        }
    };

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstoque() {
        return estoque;
    }

    public void setEstoque(String estoque) {
        this.estoque = estoque;
    }

    public String getPrecoMax() {
        return precoMax;
    }

    public void setPrecoMax(String precoMax) {
        this.precoMax = precoMax;
    }

    public String getPrecoMin() {
        return precoMin;
    }

    public void setPrecoMin(String precoMin) {
        this.precoMin = precoMin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(codigo);
        dest.writeString(descricao);
        dest.writeString(status);
        dest.writeString(estoque);
        dest.writeString(precoMax);
        dest.writeString(precoMin);
    }
}

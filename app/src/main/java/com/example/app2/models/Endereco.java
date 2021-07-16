package com.example.app2.models;

import androidx.dynamicanimation.animation.SpringAnimation;

import java.util.ArrayList;
import java.util.List;

public class Endereco {

    private int codigoEndereco;
    private String codigoCliente;
    private String endereco;
    private String numero;
    private String complememnto;
    private String bairro;
    private String codigoMunicipio;

    public Endereco() {
    }

    public Endereco(int codigoEndereco, String codigoCliente, String endereco, String numero,
                    String complememnto, String bairro, String codigoMunicipio) {
        this.codigoEndereco = codigoEndereco;
        this.codigoCliente = codigoCliente;
        this.endereco = endereco;
        this.numero = numero;
        this.complememnto = complememnto;
        this.bairro = bairro;
        this.codigoMunicipio = codigoMunicipio;
    }

    public int getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(int codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
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

    public String getComplememnto() {
        return complememnto;
    }

    public void setComplememnto(String complememnto) {
        this.complememnto = complememnto;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

}

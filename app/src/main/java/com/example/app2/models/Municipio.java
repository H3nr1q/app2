package com.example.app2.models;

public class Municipio {

    private String codigo_municipio;
    private String cidade;
    private String estado;

    public Municipio(){

    }

    public Municipio(String codigo_municipio, String cidade, String estado) {
        this.codigo_municipio = codigo_municipio;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getCodigo_municipio() {
        return codigo_municipio;
    }

    public void setCodigo_municipio(String codigo_municipio) {
        this.codigo_municipio = codigo_municipio;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

package com.example.app2.activities;

import com.example.app2.helper.EnderecoDAO;
import com.example.app2.helper.MunicipioDAO;
import com.example.app2.models.Endereco;
import com.example.app2.models.Municipio;

import java.util.List;

public class ClientePresenter {

    private Municipio municipio;
    private List<Endereco> endereco;

    private ClienteView mClienteView;

    public void buscarMunicipio(String codigo){
        municipio = MunicipioDAO.getInstance().buscarMunicipio(codigo);
        mClienteView.setMunicipio(municipio);
    }

    public void buscarEnderecos(String codigo){
        endereco = EnderecoDAO.getInstance().buscaEnderecos(codigo);
        mClienteView.setEnderecos(endereco);
    }

    public void setmClienteView(ClienteView mClienteView){
        this.mClienteView = mClienteView;
    }

    public interface ClienteView{

        void setMunicipio(Municipio municipio);
        void setEnderecos(List<Endereco> enderecos);

    }

}

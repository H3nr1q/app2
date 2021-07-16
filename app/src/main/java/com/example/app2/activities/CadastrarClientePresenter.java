package com.example.app2.activities;

import com.example.app2.helper.ClienteDAO;
import com.example.app2.helper.EnderecoDAO;
import com.example.app2.helper.MunicipioDAO;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;
import com.example.app2.models.Municipio;

import java.util.ArrayList;
import java.util.List;

public class CadastrarClientePresenter {

    private Municipio municipio;
    private List<String> municipioList = new ArrayList<>();
    private CadastrarClienteView mCadastrarClienteView;
    private List<Endereco> endereco;


    public CadastrarClientePresenter() {
    }

    public CadastrarClienteView getmCadastrarClienteView() {
        return mCadastrarClienteView;
    }

    public void setmCadastrarClienteView(CadastrarClienteView mCadastrarClienteView) {
        this.mCadastrarClienteView = mCadastrarClienteView;
    }

    public void salvarOuEditarCliente(Cliente cliente){
        ClienteDAO.getInstance().saveOrEdit(cliente);
        EnderecoDAO.getInstance().saveOrEdit(cliente.getEnderecosAdc());
    }

    public void buscarCidades(String estado){
        municipioList = MunicipioDAO.getInstance().buscarMunicipioEstado(estado);
        mCadastrarClienteView.ListarMunicipio(municipioList);
    }

    public void buscarCidadeEstado(String codigo){
        municipio = MunicipioDAO.getInstance().buscarMunicipio(codigo);
        mCadastrarClienteView.setMunicipio(municipio);
    }

    public String buscarCodMunicipio(String cidade){
        String codMunicipio = MunicipioDAO.getInstance().buscarMunicipioNome(cidade);
        return codMunicipio;
    }

    public void burcarEnderecos(String codigo){
        endereco = EnderecoDAO.getInstance().buscaEnderecos(codigo);
        mCadastrarClienteView.setEnderecos(endereco);
    }

    public interface CadastrarClienteView{

        void ListarMunicipio(List<String> municipios);

        void setMunicipio(Municipio municipio);

        void setEnderecos(List<Endereco> enderecos);

    }

}

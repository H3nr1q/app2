package com.example.app2.activities;

import com.example.app2.helper.ClienteDAO;
import com.example.app2.models.Cliente;
import com.example.app2.models.Produto;
import com.example.app2.task.CarregarClienteTask;
import com.example.app2.task.CarregarPrecosTask;
import com.example.app2.task.CarregarProdutoTask;
import com.example.app2.task.FilterClientesTask;

import java.util.List;

public class MainPresenter implements CarregarClienteTask.CarregarClientes, CarregarProdutoTask.CarregarProdutos,
        FilterClientesTask.FilterTaksView, CarregarPrecosTask.CarregarPrecosView {

    private MainView mMainView;

    public MainPresenter(MainView mMainView) {
        this.mMainView = mMainView;
    }

    public void listarClientes(){
        CarregarClienteTask carregarClienteTask = new CarregarClienteTask();
        carregarClienteTask.setCarregarClientes(this);
        carregarClienteTask.execute();
    }

    public void filterCliente(String filter){
        FilterClientesTask filterClientesTask = new FilterClientesTask(this);
        filterClientesTask.execute(filter);
    }

    public void listarProdutos(){
        CarregarProdutoTask carregarProdutoTask = new CarregarProdutoTask();
        carregarProdutoTask.setCarregarProdutos(this);
        carregarProdutoTask.execute();
    }

    public void listarPrecos(String codigo){
        CarregarPrecosTask carregarPrecosTask = new CarregarPrecosTask(this);
        carregarPrecosTask.execute(codigo);
    }

    public void deletarCliente(String codigo){
        ClienteDAO.getInstance().deletarCliente(codigo);
    }

    @Override
    public void carregaCliente(List<Cliente> clientes) {
        mMainView.listar(clientes);
    }

    @Override
    public void carregarProduto(List<Produto> produtos) {
        mMainView.listarProdutos(produtos);
    }

    @Override
    public void OnfilterTaskView(List<Cliente> clientes) {
        mMainView.listar(clientes);
    }

    @Override
    public void OnCarregarPrecos(List<String> precos) {
        mMainView.listarPrecos(precos);
    }

    public interface MainView{

        void listar(List<Cliente> clientes);

        void listarProdutos(List<Produto> produtos);

        void listarPrecos(List<String> precos);

    }

}

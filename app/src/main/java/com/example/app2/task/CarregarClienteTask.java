package com.example.app2.task;

import android.os.AsyncTask;

import com.example.app2.helper.ClienteDAO;
import com.example.app2.models.Cliente;

import java.util.List;

public class CarregarClienteTask extends AsyncTask<Void, Void, List<Cliente>> {

    private CarregarClientes carregarClientes;

    @Override
    protected List<Cliente> doInBackground(Void... voids) {
        return ClienteDAO.getInstance().obterTodos();
    }

    @Override
    protected void onPostExecute(List<Cliente> clientes) {
        super.onPostExecute(clientes);
        carregarClientes.carregaCliente(clientes);
    }

    public interface CarregarClientes{
        void carregaCliente(List<Cliente> clientes);
    }

    public void setCarregarClientes(CarregarClientes carregarClientes){
        this.carregarClientes = carregarClientes;
    }

}

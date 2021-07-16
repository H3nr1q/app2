package com.example.app2.task;

import android.os.AsyncTask;

import com.example.app2.helper.ProdutoDAO;
import com.example.app2.models.Produto;

import java.util.List;

public class CarregarProdutoTask extends AsyncTask<Void, Void, List<Produto>> {

    private CarregarProdutos carregarProdutos;

    @Override
    protected List<Produto> doInBackground(Void... voids) {
        return ProdutoDAO.getInstance().obterTodos();
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        super.onPostExecute(produtos);
        carregarProdutos.carregarProduto(produtos);
    }

    public void setCarregarProdutos(CarregarProdutos carregarProdutos){
        this.carregarProdutos = carregarProdutos;
    }

    public interface CarregarProdutos{
        void carregarProduto(List<Produto> produtos);
    }

}

package com.example.app2.task;

import android.os.AsyncTask;

import com.example.app2.helper.ProdutoDAO;

import java.util.List;

public class CarregarPrecosTask extends AsyncTask<String, Void, List<String>> {

    CarregarPrecosView carregarPrecosView;

    public CarregarPrecosTask(CarregarPrecosView carregarPrecosView) {
        this.carregarPrecosView = carregarPrecosView;
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        return ProdutoDAO.getInstance().buscarPrecos(strings[0]);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        carregarPrecosView.OnCarregarPrecos(strings);
    }

    public interface CarregarPrecosView{
        void OnCarregarPrecos(List<String> precos);
    }

}

package com.example.app2.task;

import android.os.AsyncTask;

import com.example.app2.helper.ClienteDAO;
import com.example.app2.models.Cliente;

import java.util.List;

public class FilterClientesTask extends AsyncTask<String, Void, List<Cliente>> {

    FilterTaksView filterTaksView;

    public FilterClientesTask(FilterTaksView filterTaksView) {
        this.filterTaksView = filterTaksView;
    }

    @Override
    protected List<Cliente> doInBackground(String... strings) {
        return ClienteDAO.getInstance().filtrarClientes(strings[0]);
    }

    @Override
    protected void onPostExecute(List<Cliente> clientes) {
        filterTaksView.OnfilterTaskView(clientes);
    }

    public interface FilterTaksView{
        void OnfilterTaskView(List<Cliente> clientes);
    }

}

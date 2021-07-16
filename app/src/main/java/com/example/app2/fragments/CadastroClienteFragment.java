package com.example.app2.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.app2.activities.CadastrarClientePresenter;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;
import com.example.app2.models.ICadastrarClientePresenter;
import com.example.app2.models.ICadastroCliente;
import com.example.app2.models.ICadastroEndereco;

import org.jetbrains.annotations.NotNull;

public abstract class CadastroClienteFragment extends Fragment {

    private ICadastroCliente mCadastroCliente;
    private ICadastroEndereco mCadastroEndereco;
    private ICadastrarClientePresenter mCadastrarClientePresenter;

    public CadastroClienteFragment() {
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        if (context instanceof ICadastroCliente){
            mCadastroCliente = (ICadastroCliente) context;
        }
        if (context instanceof ICadastroEndereco){
            mCadastroEndereco = (ICadastroEndereco) context;
        }
        if (context instanceof ICadastrarClientePresenter){
            mCadastrarClientePresenter = (ICadastrarClientePresenter) context;
        }
    }

    protected Cliente getCliente(){
        return mCadastroCliente.getCliente();
    }

    protected Endereco getEndereco(){
        return mCadastroEndereco.getEndereco();
    }

    protected CadastrarClientePresenter getCadastrarClientePresenter(){
        return mCadastrarClientePresenter.getCadastrarClientePresenter();
    }

    public abstract boolean isValid();

}

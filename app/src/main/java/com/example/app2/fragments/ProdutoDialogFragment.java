package com.example.app2.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.example.app2.activities.MainPresenter;
import com.example.app2.adapters.AdapterPrecos;
import com.example.app2.models.Cliente;
import com.example.app2.models.Produto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDialogFragment extends DialogFragment implements View.OnClickListener, MainPresenter.MainView {

    static View mView;
    static String codProduto;
    static List<String> mPrecoslist = new ArrayList<>();
    static AdapterPrecos mAdapterPrecos;
    static RecyclerView mRecyclerView;
    static MainPresenter mMainPresenter;

    public ProdutoDialogFragment() {
    }

    public static ProdutoDialogFragment newInstance(String codigo){
        ProdutoDialogFragment produtoDialogFragment = new ProdutoDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("codigoProduto", codigo);
        produtoDialogFragment.setArguments(bundle);
        return produtoDialogFragment;
    }

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            codProduto = getArguments().getString("codigoProduto");
        }

        mMainPresenter = new MainPresenter(this);

    }

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        mView = inflater.inflate(R.layout.fragment_produto_dialog, null, false);
        mRecyclerView = mView.findViewById(R.id.rvPrecos);
        mAdapterPrecos = new AdapterPrecos(mPrecoslist);
        mAdapterPrecos.setmPrecoList(mPrecoslist);
        mMainPresenter.listarPrecos(codProduto);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        mRecyclerView.setAdapter(mAdapterPrecos);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Precos produto: " + codProduto);
        builder.setView(mView);
        builder.setNegativeButton("Fechar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void listar(List<Cliente> clientes) {

    }

    @Override
    public void listarProdutos(List<Produto> produtos) {

    }

    @Override
    public void listarPrecos(List<String> precos) {
        mAdapterPrecos.refreshAdapterList(precos);
    }
}

package com.example.app2.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.app2.R;
import com.example.app2.activities.MainPresenter;
import com.example.app2.adapters.AdapterProdutos;
import com.example.app2.models.Cliente;
import com.example.app2.models.Produto;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProdutoFragment extends Fragment implements MainPresenter.MainView, AdapterProdutos.OnProdutoClickListener {

    private AdapterProdutos mAdapterProdutos;
    private RecyclerView mRecyclerView;
    private MainPresenter mMainPresenter;
    private ArrayList<Produto> mProdutoList = new ArrayList<>();
    private Parcelable savedRecyclerLayoutState;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        verificaInstanceState(savedInstanceState);
        mMainPresenter = new MainPresenter(this);
        mMainPresenter.listarProdutos();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produto, container, false);


        mAdapterProdutos = new AdapterProdutos(mProdutoList);
        mAdapterProdutos.setmOnProdutoClickListener(this);
        mRecyclerView = view.findViewById(R.id.rvProdutos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapterProdutos);

        mMainPresenter.listarProdutos();

        restoreLayoutManegerPosition();

        return view;
    }


    @Override
    public void listar(List<Cliente> clientes) {

    }

    @Override
    public void listarProdutos(List<Produto> produtos) {
        mAdapterProdutos.refreshAdapterList(produtos);
        mProdutoList.addAll(produtos);
    }

    @Override
    public void listarPrecos(List<String> precos) {

    }

    @Override
    public void onProdutoItemSelected(int position, Produto produto) {
        ProdutoDialogFragment produtoDialogFragment = ProdutoDialogFragment.newInstance(produto.getCodigo());
        produtoDialogFragment.show(getActivity().getSupportFragmentManager(), "codigoProduto");
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList("rvList", mProdutoList);
        outState.putParcelable("recycler_layout", mRecyclerView.getLayoutManager().onSaveInstanceState());

    }

    private void verificaInstanceState(Bundle savedInstanceState){

        if (savedInstanceState != null){
            mProdutoList = savedInstanceState.getParcelableArrayList("rvList");
            savedRecyclerLayoutState = savedInstanceState.getParcelable("recycler_layout");
        }

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        if (savedInstanceState != null){
            mProdutoList = savedInstanceState.getParcelableArrayList("rvList");
            savedRecyclerLayoutState = savedInstanceState.getParcelable("recycler_layout");
        }

        super.onActivityCreated(savedInstanceState);

    }

    private void restoreLayoutManegerPosition(){
        if (savedRecyclerLayoutState != null){
            mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }
}
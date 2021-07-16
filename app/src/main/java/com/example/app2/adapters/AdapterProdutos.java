package com.example.app2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.example.app2.activities.MainPresenter;
import com.example.app2.models.Cliente;
import com.example.app2.models.Produto;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.MyViewHolder>
        implements MainPresenter.MainView {

    private List<Produto> mListProduto;
    private OnProdutoClickListener mOnProdutoClickListener;

    public AdapterProdutos(List<Produto> mListProduto) {
        this.mListProduto = mListProduto;
    }

    public void refreshAdapterList(List<Produto> produtos){
        mListProduto = produtos;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public AdapterProdutos.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                           int viewType) {

        View produtoList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_produtos, parent, false);

        return new MyViewHolder(produtoList);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterProdutos.MyViewHolder holder, int position) {

        Produto produto = mListProduto.get(position);
        holder.codigo.setText(produto.getCodigo());
        holder.descricao.setText(produto.getDescricao());
        holder.estoque.setText(produto.getEstoque());
        holder.precoMax.setText("R$ "+produto.getPrecoMax());
        holder.precoMin.setText("R$ "+produto.getPrecoMin());

    }

    @Override
    public int getItemCount() {
        return mListProduto.size();
    }

    @Override
    public void listar(List<Cliente> clientes) {

    }

    @Override
    public void listarProdutos(List<Produto> produtos) {

    }

    @Override
    public void listarPrecos(List<String> precos) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView codigo, descricao, estoque, precoMax, precoMin;


        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            codigo = itemView.findViewById(R.id.tvCodigo);
            descricao = itemView.findViewById(R.id.tvDescricao);
            estoque = itemView.findViewById(R.id.tvQuantEstoque);
            precoMax = itemView.findViewById(R.id.tvValorPrecoMax);
            precoMin = itemView.findViewById(R.id.tvValorPrecoMin);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnProdutoClickListener.onProdutoItemSelected(
                            getAdapterPosition(),
                            mListProduto.get(getAdapterPosition())
                    );
                }
            });

        }
    }

    public interface OnProdutoClickListener{
        void onProdutoItemSelected(int position, Produto produto);
    }

    public void setmOnProdutoClickListener(OnProdutoClickListener mOnProdutoClickListener) {
        this.mOnProdutoClickListener = mOnProdutoClickListener;
    }

}

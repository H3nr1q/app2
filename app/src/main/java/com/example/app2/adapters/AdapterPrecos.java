package com.example.app2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.example.app2.models.Cliente;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterPrecos extends RecyclerView.Adapter<AdapterPrecos.MyViewHolder> {

    List<String> mPrecoList;

    public AdapterPrecos(List<String> mPrecoList) {
        this.mPrecoList = mPrecoList;
    }

    public void refreshAdapterList(List<String> precos){
        mPrecoList = precos;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public AdapterPrecos.MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent,
                                                         int viewType) {

        View listPreco = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_preco, parent, false);

        return new MyViewHolder(listPreco);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterPrecos.MyViewHolder holder, int position) {
        String preco = mPrecoList.get(position);
        holder.preco.setText(preco);

    }

    @Override
    public int getItemCount() {
        return mPrecoList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView preco;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            preco = itemView.findViewById(R.id.tvPreco);
        }
    }

    public void setmPrecoList(List<String> mPrecoList) {
        this.mPrecoList = mPrecoList;
    }
}

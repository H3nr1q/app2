package com.example.app2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app2.R;
import com.example.app2.activities.ClientePresenter;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;
import com.example.app2.models.Municipio;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterClientes extends RecyclerView.Adapter<AdapterClientes.MyViewHolder>
        implements ClientePresenter.ClienteView {


    private List<Cliente> mListCliente;
    private OnClienteClickListener onClienteClickListener;
    private String mCidade, mEstado;
    private ClientePresenter mClientePresenter;

    public AdapterClientes(List<Cliente> mListCliente) {
        this.mListCliente = mListCliente;
    }

    public void refreshAdapterList(List<Cliente> clientes){
        mListCliente = clientes;
        notifyDataSetChanged();
    }

    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder( @NotNull ViewGroup parent, int viewType) {

        View clienteList = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_clientes, parent, false);
        mClientePresenter = new ClientePresenter();

        return new MyViewHolder(clienteList);
    }

    @Override
    public void onBindViewHolder(@NotNull MyViewHolder holder, int position) {

        Cliente cliente = mListCliente.get(position);
        holder.codigoCliente.setText(cliente.getCodigo_cliente());
        holder.nome.setText(cliente.getNome_fantasia());
        holder.razaoSocial.setText(cliente.getRazao_social());
        holder.cpf.setText(cliente.getCpf());
        holder.bairro.setText(cliente.getBairro());
        mClientePresenter.setmClienteView(this);

        mClientePresenter.buscarMunicipio(cliente.getCodigo_municipio());
        holder.cidade.setText(mCidade+", "+mEstado);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClienteClickListener != null){
                    onClienteClickListener.onClick(mListCliente.get(position));
                }
            }
        });

        holder.imbMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClienteClickListener.onPopupMenuClick(v, mListCliente.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListCliente.size();
    }

    public void setOnClienteClickListener(OnClienteClickListener onClienteClickListener){
        this.onClienteClickListener = onClienteClickListener;
    }

    public void deleteContato(Cliente cliente) {
        int index = mListCliente.indexOf(cliente);
        if(index >= 0) {
            mListCliente.remove(index);
            notifyItemRemoved(index);
            notifyItemRangeChanged(index, getItemCount());
        }
    }

    @Override
    public void setMunicipio(Municipio municipio) {

        mCidade = municipio.getCidade();
        mEstado = municipio.getEstado();

    }

    @Override
    public void setEnderecos(List<Endereco> enderecos) {

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView codigoCliente, nome, razaoSocial, cpf, bairro, cidade;
        ImageButton imbMenu;

        public MyViewHolder( @NotNull View itemView) {
            super(itemView);

            codigoCliente = itemView.findViewById(R.id.tvCodigoCliente);
            nome = itemView.findViewById(R.id.tvNomeFantasia);
            razaoSocial = itemView.findViewById(R.id.tvRazaoSocial);
            cpf = itemView.findViewById(R.id.tvCPF);
            bairro = itemView.findViewById(R.id.tvBairro);
            cidade = itemView.findViewById(R.id.tvCidade);
            imbMenu = itemView.findViewById(R.id.btnMenu);

        }
    }

    public List<Cliente> getmListCliente() {
        return mListCliente;
    }

    public interface OnClienteClickListener{
        void onClick(Cliente cliente);
        void onPopupMenuClick(View view, Cliente pos);
    }

}

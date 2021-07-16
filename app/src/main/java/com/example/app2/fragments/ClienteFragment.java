package com.example.app2.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.app2.R;
import com.example.app2.activities.CadastrarClienteActivity;
import com.example.app2.activities.ClienteActivity;
import com.example.app2.activities.ClientePresenter;
import com.example.app2.activities.MainPresenter;
import com.example.app2.adapters.AdapterClientes;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;
import com.example.app2.models.Municipio;
import com.example.app2.models.Produto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ClienteFragment extends Fragment implements MainPresenter.MainView, ClientePresenter.ClienteView ,
        AdapterClientes.OnClienteClickListener, SearchView.OnQueryTextListener{

    private AdapterClientes mAdapterClientes;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private View mView1, mView2, mView3, mView4;
    private FrameLayout mFrameLayout1, mFrameLayout2;
    private TextView nome, nomeFantasia, cpfOucnpj, codigoCliente, emailPrincipal,
            emailSecundario, enderecoPrincipal, enderecoAdc1, enderecoAdc2;
    private String cidade, estado;
    private MainPresenter mMainPresenter = new MainPresenter(this);
    private ClientePresenter mClientePresenter;
    private ArrayList<Cliente> mClienteList = new ArrayList<>();
    private FloatingActionButton mAddCliente;
    private Configuration configuration;
    private boolean verificaView = false;
    private SearchView searchView;
    private String mPesquisar;
    private Parcelable savedRecyclerLayoutState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        verificaInstanceState(savedInstanceState);
        mMainPresenter.listarClientes();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        searchView = view.findViewById(R.id.searchCliente);
        mFrameLayout1 = view.findViewById(R.id.flClientes);
        mView1 = getLayoutInflater().inflate(R.layout.seleciona_cliente, null);

        mClientePresenter = new ClientePresenter();

        configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            mFrameLayout1.addView(mView1);
        }
        mAdapterClientes = new AdapterClientes(mClienteList);
        mAdapterClientes.setOnClienteClickListener(this);

        mRecyclerView = view.findViewById(R.id.rvListarClientes);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapterClientes);

        mMainPresenter.listarClientes();

        mAddCliente = view.findViewById(R.id.fabAddCliente);

        mAddCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mostrarDialog();

            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mAddCliente.getVisibility() == View.VISIBLE){
                    mAddCliente.hide();
                }else if(dy < 0 && mAddCliente.getVisibility() != View.VISIBLE){
                    mAddCliente.show();
                }
            }
        });

        restoreLayoutManegerPosition();

        return view;
    }


    @Override
    public void listar(List<Cliente> clientes) {
        mAdapterClientes.refreshAdapterList(clientes);
        mClienteList.addAll(clientes);
    }

    @Override
    public void listarProdutos(List<Produto> produtos) {

    }

    @Override
    public void listarPrecos(List<String> precos) {

    }

    public void mostrarDialog(){

        ClienteDialogFragment dialogFragment = new ClienteDialogFragment();
        dialogFragment.show(getActivity().getSupportFragmentManager(), "clienteDialog");

    }

    @Override
    public void onClick(Cliente cliente) {

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

            if (!verificaView){
                mFrameLayout1.removeView(mView1);
                mView2 = getLayoutInflater().inflate(R.layout.activity_cliente, null);
                mFrameLayout1.addView(mView2);
                verificaView = true;
                preencherActivityCliente(cliente);
            } else {
                mFrameLayout1.removeView(mView2);
                mView2 = getLayoutInflater().inflate(R.layout.activity_cliente, null);
                mFrameLayout1.addView(mView2);
                preencherActivityCliente(cliente);
            }


        } else {
            Intent intent = new Intent(getActivity(), ClienteActivity.class);
            intent.putExtra("cliente", cliente);
            startActivity(intent);
        }

    }

    @Override
    public void onPopupMenuClick(View view, Cliente cliente) {

        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_cliente, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.itEditar:

                        Intent intent = new Intent(getActivity(), CadastrarClienteActivity.class);
                        intent.putExtra("clienteEditar", cliente);
                        startActivity(intent);
                        //Toast.makeText(getContext(), "Editar Cliente: " + cliente.getRazao_social(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.itDeletar:
                        Toast.makeText(getContext(), "Deletar Cliente: " + cliente.getRazao_social(), Toast.LENGTH_SHORT).show();
                        mMainPresenter.deletarCliente(cliente.getCodigo_cliente());
                        mAdapterClientes.deleteContato(cliente);
                        break;
                    default:

                }
                return false;
            }
        });
        popupMenu.show();

    }

    private void preencherActivityCliente(Cliente cliente) {

        Cliente pos = cliente;

        mFrameLayout2 = mView2.findViewById(R.id.flTipoCliente);
        mView3 = getLayoutInflater().inflate(R.layout.cliente_cnpj, null);
        mView4 = getLayoutInflater().inflate(R.layout.cliente_cpf, null);

        if (cliente.getTipo_pessoa().equals("J") ){

            mFrameLayout2.addView(mView3);
            nome = mView2.findViewById(R.id.tvRazaoSocial);
            nomeFantasia = mView2.findViewById(R.id.tvNomeFantasia);
            cpfOucnpj = mView2.findViewById(R.id.tvNumberCNPJ);

            nomeFantasia.setText(cliente.getNome_fantasia());

        } else {
            mFrameLayout2.addView(mView4);
            nome = mView2.findViewById(R.id.tvNomeCliente);
            cpfOucnpj = mView2.findViewById(R.id.tvNumberCPF);
        }

        codigoCliente = mView2.findViewById(R.id.tvCodigoCliente);
        emailPrincipal = mView2.findViewById(R.id.tvFirstEmail);
        emailSecundario = mView2.findViewById(R.id.tvSecEmail);
        enderecoPrincipal = mView2.findViewById(R.id.tvAdress);
        enderecoAdc1 = mView2.findViewById(R.id.tvAdressAdc1);
        enderecoAdc2 = mView2.findViewById(R.id.tvAdressAdc2);

        nome.setText(cliente.getRazao_social());
        cpfOucnpj.setText(cliente.getCpf());
        codigoCliente.setText(cliente.getCodigo_cliente());
        emailPrincipal.setText(cliente.getEmail_pricipal());
        emailSecundario.setText(cliente.getEmail_secundario());

        String codigo = cliente.getCodigo_municipio();

        mClientePresenter = new ClientePresenter();

        mClientePresenter.setmClienteView(this);

        mClientePresenter.buscarMunicipio(codigo);


        String complemento;
        if (cliente.getComplemento() != null){
            complemento = ", "+cliente.getComplemento().trim()+", ";
        } else {
            complemento = ", ";
        }


        enderecoPrincipal.setText(cliente.getEndereco().trim()+", "+cliente.getNumero().trim()+
                complemento+cliente.getBairro().trim()+", "+
                cidade+", "+estado+", "+cliente.getCEP().trim());

        mClientePresenter.buscarEnderecos(cliente.getCodigo_cliente());


    }

    @Override
    public void setMunicipio(Municipio municipio) {

        if (municipio != null){

            cidade = municipio.getCidade().trim();
            estado = municipio.getEstado().trim();

        }

    }

    @Override
    public void setEnderecos(List<Endereco> enderecos) {

        String complemento;

        if (enderecos.size() != 0 && enderecos.size() == 2){

            if (enderecos.get(0).getCodigoMunicipio() != null){
                mClientePresenter.buscarMunicipio(enderecos.get(0).getCodigoMunicipio().trim());
            }

            if (enderecos.get(0).getComplememnto() != null){
                complemento = ", "+enderecos.get(0).getComplememnto().trim()+", ";
            } else {
                complemento = ", ";
            }

            enderecoAdc1.setText(enderecos.get(0).getEndereco().trim()+", "+
                    enderecos.get(0).getNumero().trim()+
                    complemento+
                    enderecos.get(0).getBairro().trim()+", "+
                    cidade+", "+estado);


            if (enderecos.get(1).getCodigoMunicipio() != null ){
                mClientePresenter.buscarMunicipio(enderecos.get(1).getCodigoMunicipio().trim());
            }

            if (enderecos.get(1).getComplememnto() != null){
                complemento = ", "+enderecos.get(1).getComplememnto().trim()+", ";
            } else {
                complemento = ", ";
            }

            enderecoAdc2.setText(enderecos.get(1).getEndereco().trim()+", "+
                    enderecos.get(1).getNumero().trim()+
                    complemento+
                    enderecos.get(1).getBairro().trim()+", "+
                    cidade+","+estado);

        } else if (enderecos.size() != 0 && enderecos.size() == 1){

            if (enderecos.get(0).getComplememnto() != null){
                complemento = ", "+enderecos.get(0).getComplememnto().trim()+", ";
            } else {
                complemento = ", ";
            }

            if (enderecos.get(0).getCodigoMunicipio() != null){
                mClientePresenter.buscarMunicipio(enderecos.get(0).getCodigoMunicipio().trim());
            }

            enderecoAdc1.setText(enderecos.get(0).getEndereco().trim()+", "+
                    enderecos.get(0).getNumero().trim()+
                    complemento+
                    enderecos.get(0).getBairro().trim()+", "+
                    cidade+","+estado);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.listarClientes();
    }

    @Override
    public void onSaveInstanceState(@NonNull @NotNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mPesquisar != null){
            outState.putString("searchView", mPesquisar);
        }

        if (mAdapterClientes.getmListCliente() != null && !mAdapterClientes.getmListCliente().isEmpty()){
            outState.putParcelableArrayList("rvList", new ArrayList<>(mAdapterClientes.getmListCliente()));
        }


        if (mRecyclerView.getLayoutManager() != null){
            outState.putParcelable("recycler_layout", mRecyclerView.getLayoutManager().onSaveInstanceState());
        }

    }

    private void verificaInstanceState(Bundle savedInstanceState){

        if (savedInstanceState != null){
            mPesquisar = savedInstanceState.getString("searchView");
            mClienteList = savedInstanceState.getParcelableArrayList("rvList");
            savedRecyclerLayoutState = savedInstanceState.getParcelable("recycler_layout");
        }else{
            mPesquisar = "";
            mMainPresenter.listarClientes();
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.clientes_menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.searchCliente);
        searchView = (SearchView) searchViewItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);

        if (mPesquisar!= null && !mPesquisar.isEmpty()){
            searchView.onActionViewExpanded();
            searchView.post(new Runnable() {
                @Override
                public void run() {
                    searchView.setQuery(mPesquisar, false);
                }
            });
        }

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                mPesquisar = "";
                return false;
            }
        });

        searchView.setOnQueryTextListener(this);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPesquisar = newText;
        if (newText != null && !newText.isEmpty()){
            mPesquisar = newText;
            mMainPresenter.filterCliente(mPesquisar);
        }else{
            mPesquisar = "";
            searchView.setIconified(true);
            searchView.clearFocus();
        }
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        if (savedInstanceState != null){
            mClienteList = savedInstanceState.getParcelableArrayList("rvList");
            savedRecyclerLayoutState = savedInstanceState.getParcelable("recycler_layout");
        }

        super.onActivityCreated(savedInstanceState);

    }

    private void restoreLayoutManegerPosition(){
        if (savedRecyclerLayoutState != null){
            if (mRecyclerView.getLayoutManager() != null){
                mRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
            }
        }
    }

}
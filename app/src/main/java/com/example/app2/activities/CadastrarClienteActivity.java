package com.example.app2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.app2.R;
import com.example.app2.adapters.TabsAddClientAdapter;
import com.example.app2.fragments.CadastroClienteBasicosFragment;
import com.example.app2.fragments.CadastroClienteFragment;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;
import com.example.app2.models.ICadastrarClientePresenter;
import com.example.app2.models.ICadastroCliente;
import com.example.app2.models.ICadastroEndereco;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CadastrarClienteActivity extends AppCompatActivity implements ICadastroCliente,
        ICadastroEndereco {

    private TabsAddClientAdapter mAdapter;
    private Cliente cliente;
    private Endereco endereco;
    private ViewPager pager;
    private String codeCliente;

    private CadastrarClientePresenter cadastrarClientePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        // Tabs
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.viewpager);
        mAdapter = new TabsAddClientAdapter(getSupportFragmentManager());

        pager.setAdapter(mAdapter);
        tabs.setupWithViewPager(pager);

        if (getIntent().getParcelableExtra("clienteEditar") == null){
            getSupportActionBar().setTitle(
                    "Novo Cliente "+getIntent().getStringExtra("cpfoucnpj"));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            cliente = new Cliente();
            endereco = new Endereco();
        } else {
            cliente = getIntent().getParcelableExtra("clienteEditar");
            getSupportActionBar().setTitle("Editar cliente: " + cliente.getCpf());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        cadastrarClientePresenter = new CadastrarClientePresenter();

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                validaCliente(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        if (cliente.getCodigo_cliente() == null){
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.getDefault());
            codeCliente = df.format(new Date());

            cliente.setCodigo_cliente(codeCliente);
        }

    }

    public boolean validaCliente(int position){

        if (position > 0){

            for (int i = 0; i < position; i++){

                Fragment fragment = getPage(i);

                if (fragment instanceof CadastroClienteFragment){
                    if (!((CadastroClienteFragment) fragment).isValid()){
                        pager.setCurrentItem(i);
                        return false;
                    }
                }

            }
        } else {

            Fragment fragment = getPage(pager.getCurrentItem());

            if (fragment instanceof CadastroClienteFragment){
                if (!((CadastroClienteFragment) fragment).isValid()){
                    pager.setCurrentItem(pager.getCurrentItem());
                }
            }

            return false;

        }
        return true;
    }

    public void salvarCliente(){

        if (cliente != null){
            cadastrarClientePresenter.salvarOuEditarCliente(cliente);
        } else {
            Toast.makeText(this, "Erro ao salvar o Cliente", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean clienteValido(){

        for (int i = 0; i < mAdapter.getCount(); i++){
            Fragment fragment = getPage(i);
            if (fragment instanceof CadastroClienteFragment){
                if (!((CadastroClienteFragment) fragment).isValid()){
                    pager.setCurrentItem(i);
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Cliente getCliente() {
            return cliente;
    }

    @Override
    public Endereco getEndereco() {
        return endereco;
    }

    public Fragment getPage(int position){
        return (Fragment) mAdapter.instantiateItem(pager, position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_salvar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.itSalvar:
                if (clienteValido()){
                    salvarCliente();
                    Toast.makeText(getApplicationContext(), "Salvo com sucesso",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Erro ao salvar",
                            Toast.LENGTH_SHORT).show();
                }

            case android.R.id.home:
                finish();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

}
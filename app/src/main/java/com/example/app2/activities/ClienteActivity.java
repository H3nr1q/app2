package com.example.app2.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app2.R;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;
import com.example.app2.models.Municipio;

import java.util.List;

public class ClienteActivity extends AppCompatActivity implements ClientePresenter.ClienteView {

    private Cliente cliente;
    private FrameLayout mFrameLayout;
    private View mView1, mView2;
    private TextView nome, nomeFantasia, cpfOucnpj, codigoCliente, emailPrincipal,
            emailSecundario, enderecoPrincipal, enderecoAdc1, enderecoAdc2;
    private ClientePresenter mClientePresenter;
    private String cidade, estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mFrameLayout = (FrameLayout) findViewById(R.id.flTipoCliente);
        mView1 = getLayoutInflater().inflate(R.layout.cliente_cpf, null);
        mView2 = getLayoutInflater().inflate(R.layout.cliente_cnpj, null);

        if (getIntent() != null){
            cliente = getIntent().getExtras().getParcelable("cliente");
        }

        if (cliente.getTipo_pessoa().equals("J") ){
            mFrameLayout.addView(mView2);
            nome = findViewById(R.id.tvRazaoSocial);
            nomeFantasia = findViewById(R.id.tvNomeFantasia);
            cpfOucnpj = findViewById(R.id.tvNumberCNPJ);

            nomeFantasia.setText(cliente.getNome_fantasia());

        } else {
            mFrameLayout.addView(mView1);
            nome = findViewById(R.id.tvNomeCliente);
            cpfOucnpj = findViewById(R.id.tvNumberCPF);
        }

        codigoCliente = findViewById(R.id.tvCodigoCliente);
        emailPrincipal = findViewById(R.id.tvFirstEmail);
        emailSecundario = findViewById(R.id.tvSecEmail);
        enderecoPrincipal = findViewById(R.id.tvAdress);
        enderecoAdc1 = findViewById(R.id.tvAdressAdc1);
        enderecoAdc2 = findViewById(R.id.tvAdressAdc2);

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
            complemento = " "+cliente.getComplemento().trim()+", ";
        } else {
            complemento = ", ";
        }

        String enderecoPrinc = cliente.getEndereco().trim()+", "
                +cliente.getNumero().trim()
                + complemento+cliente.getBairro().trim()+", "
                + cidade+", "+estado+", "+cliente.getCEP().trim();

        enderecoPrincipal.setText(enderecoPrinc);

        mClientePresenter.buscarEnderecos(cliente.getCodigo_cliente());

    }

    @Override
    public void setMunicipio(Municipio municipio) {

        if (municipio != null){

            cidade = municipio.getCidade().trim();
            estado = municipio.getEstado().trim();

        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void setEnderecos(List<Endereco> enderecos) {

        if (enderecos.size() != 0 && enderecos.size() == 2){

            String complemento;

            mClientePresenter.buscarMunicipio(enderecos.get(0).getCodigoMunicipio().trim());

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

            mClientePresenter.buscarMunicipio(enderecos.get(1).getCodigoMunicipio().trim());

            if (enderecos.get(1).getComplememnto() != null){
                complemento = ", "+enderecos.get(1).getComplememnto().trim()+", ";
            } else {
                complemento = ", ";
            }

            enderecoAdc2.setText(enderecos.get(1).getEndereco().trim()+", "+
                    enderecos.get(1).getNumero().trim()+
                    complemento+
                    enderecos.get(1).getBairro().trim()+", "+
                    cidade+", "+estado);

        }

        if (enderecos.size() != 0 && enderecos.size() == 1){

            String complemento;

            mClientePresenter.buscarMunicipio(enderecos.get(0).getCodigoMunicipio().trim());

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

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:break;
        }
        return true;
    }
}
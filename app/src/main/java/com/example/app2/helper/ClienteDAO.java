package com.example.app2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app2.app.App2app;
import com.example.app2.database.DbHelper;
import com.example.app2.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private DbHelper mDbHelper;
    private SQLiteDatabase mDqLiteDatabase;
    private final String TABELA_BANCO = "GUA_CLIENTES";
    private static final String KEY_CODIGOCLIENTE = "CLI_CODIGOCLIENTE";
    private static final String KEY_RAZAOSOCIAL = "CLI_RAZAOSOCIAL";
    private static final String KEY_CGCCPF = "CLI_CGCCPF";
    private static final String KEY_ENDERECO = "CLI_ENDERECO";
    private static final String KEY_NUMERO = "CLI_NUMERO";
    private static final String KEY_COMPLEMENTO = "CLI_COMPLEMENTO";
    private static final String KEY_BAIRRO = "CLI_BAIRRO";
    private static final String KEY_CODIGOMUNICIPIO = "CLI_CODIGOMUNICIPIO";
    private static final String KEY_CEP = "CLI_CEP";
    private static final String KEY_EMAIL= "CLI_EMAIL";
    private static final String KEY_EMAILSECUNDARIO= "CLI_EMAILSECUNDARIO";
    private static final String KEY_PESSOA= "CLI_PESSOA";
    private static final String KEY_NOMEFANTASIA= "CLI_NOMEFANTASIA";

    private static ClienteDAO sInstance;

    public static synchronized ClienteDAO getInstance(){
        if(sInstance == null){
            sInstance = new ClienteDAO();
        }
        return sInstance;
    }

    public ClienteDAO(){
    }

    public List<Cliente> obterTodos(){
        List<Cliente> clientes = new ArrayList<>();

        Cursor cursor = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().query(TABELA_BANCO, new String[]{
                KEY_CODIGOCLIENTE, KEY_RAZAOSOCIAL, KEY_CGCCPF, KEY_ENDERECO, KEY_NUMERO,
                KEY_COMPLEMENTO, KEY_BAIRRO, KEY_CODIGOMUNICIPIO, KEY_CEP, KEY_EMAIL,
                KEY_EMAILSECUNDARIO, KEY_PESSOA, KEY_NOMEFANTASIA
        }, null, null, null, null, null);

        while (cursor.moveToNext()){
            clientes.add(bind(cursor));
        }
        cursor.close();
        return clientes;
    }

    public boolean saveOrEdit(Cliente cliente) {

        try {

            mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                    .getWritableDatabase().insertWithOnConflict(TABELA_BANCO,
                    null, bindValues(cliente), SQLiteDatabase.CONFLICT_REPLACE);

        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar o cliente" + e.getMessage());
            return false;
        }
        return true;
    }

    public void deletarCliente(String codigo){

        mDbHelper.getReadableDatabase().delete(TABELA_BANCO, "CLI_CODIGOCLIENTE = ?",
                new String[]{String.valueOf(codigo)});

    }

    public List<Cliente> filtrarClientes(String filter){

        List<Cliente> clientes = new ArrayList<>();

        String sql = "SELECT * FROM " + TABELA_BANCO + "" +
                " WHERE "+ KEY_CGCCPF +" LIKE ? OR " +
                " "+ KEY_RAZAOSOCIAL +" LIKE ? OR " +
                " "+ KEY_NOMEFANTASIA +" LIKE ? ;";

        Cursor cursor = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().rawQuery(sql, new String[]{filter.concat("%"),
        filter.concat("%"), filter.concat("%")});

        while (cursor.moveToNext()){
            clientes.add(bind(cursor));
        }

        return clientes;
    }

    protected Cliente bind(Cursor cursor){

        Cliente cliente = new Cliente();
        cliente.setCodigo_cliente(cursor.getString(cursor.getColumnIndex(KEY_CODIGOCLIENTE)));
        cliente.setRazao_social(cursor.getString(cursor.getColumnIndex(KEY_RAZAOSOCIAL)));
        cliente.setCpf(cursor.getString(cursor.getColumnIndex(KEY_CGCCPF)));
        cliente.setEndereco(cursor.getString(cursor.getColumnIndex(KEY_ENDERECO)));
        cliente.setNumero(cursor.getString(cursor.getColumnIndex(KEY_NUMERO)));
        cliente.setComplemento(cursor.getString(cursor.getColumnIndex(KEY_COMPLEMENTO)));
        cliente.setBairro(cursor.getString(cursor.getColumnIndex(KEY_BAIRRO)));
        cliente.setCodigo_municipio(cursor.getString(cursor.getColumnIndex(KEY_CODIGOMUNICIPIO)));
        cliente.setCEP(cursor.getString(cursor.getColumnIndex(KEY_CEP)));
        cliente.setEmail_pricipal(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
        cliente.setEmail_secundario(cursor.getString(cursor.getColumnIndex(KEY_EMAILSECUNDARIO)));
        cliente.setTipo_pessoa(cursor.getString(cursor.getColumnIndex(KEY_PESSOA)));
        cliente.setNome_fantasia(cursor.getString(cursor.getColumnIndex(KEY_NOMEFANTASIA)));

        return cliente;
    }

    protected ContentValues bindValues(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put(KEY_CODIGOCLIENTE, cliente.getCodigo_cliente());
        values.put(KEY_RAZAOSOCIAL, cliente.getRazao_social());
        values.put(KEY_NOMEFANTASIA, cliente.getNome_fantasia());
        values.put(KEY_CGCCPF, cliente.getCpf());
        values.put(KEY_EMAIL, cliente.getEmail_pricipal());
        values.put(KEY_EMAILSECUNDARIO, cliente.getEmail_secundario());
        values.put(KEY_ENDERECO, cliente.getEndereco());
        values.put(KEY_NUMERO, cliente.getNumero());
        values.put(KEY_COMPLEMENTO, cliente.getComplemento());
        values.put(KEY_BAIRRO, cliente.getBairro());
        values.put(KEY_CODIGOMUNICIPIO, cliente.getCodigo_municipio());
        values.put(KEY_CEP, cliente.getCEP());
        values.put(KEY_PESSOA, cliente.getTipo_pessoa());

        return values;
    }

}

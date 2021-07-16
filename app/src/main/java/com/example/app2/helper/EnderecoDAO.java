package com.example.app2.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.app2.app.App2app;
import com.example.app2.database.DbHelper;
import com.example.app2.models.Cliente;
import com.example.app2.models.Endereco;

import java.util.ArrayList;
import java.util.List;

import static com.example.app2.helper.ProdutoDAO.sInstance;

public class EnderecoDAO {

    private DbHelper mDbHelper;
    private SQLiteDatabase mDqLiteDatabase;

    private final String TABELA_BANCO = "GUA_ENDERECOSADICIONAIS";
    private static final String KEY_CODIGO = "EAD_CODIGO";
    private static final String KEY_CODIGOCLIENTE = "EAD_CODIGOCLIENTE";
    private static final String KEY_ENDERECO = "EAD_ENDERECO";
    private static final String KEY_NUMERO = "EAD_NUMERO";
    private static final String KEY_COMPLEMENTO = "EAD_COMPLEMENTO";
    private static final String KEY_BAIRRO = "EAD_BAIRRO";
    private static final String KEY_CODIGOMUNICIPIO = "EAD_CODIGOMUNICIPIO";

    private static EnderecoDAO sInstance;

    public static synchronized EnderecoDAO getInstance(){
        if(sInstance == null){
            sInstance = new EnderecoDAO();
        }
        return sInstance;
    }

    public EnderecoDAO() {
    }

    public List<Endereco> buscaEnderecos(String codigo){

        List<Endereco> enderecos = new ArrayList<>();

        String sql = "SELECT * FROM " + TABELA_BANCO + " WHERE EAD_CODIGOCLIENTE = \""+codigo+"\"";

        Cursor c = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().rawQuery(sql, null);

        while(c.moveToNext()){
            enderecos.add(bind(c));
        }
        c.close();
        return enderecos;
    }

    public boolean saveOrEdit(List<Endereco> endereco) {

        try {

            for (int i = 0; i < endereco.size(); i++){

                ContentValues values = new ContentValues();
                values.put(KEY_CODIGO, endereco.get(i).getCodigoEndereco());
                values.put(KEY_CODIGOCLIENTE, endereco.get(i).getCodigoCliente());
                values.put(KEY_ENDERECO, endereco.get(i).getEndereco());
                values.put(KEY_NUMERO, endereco.get(i).getNumero());
                values.put(KEY_COMPLEMENTO, endereco.get(i).getComplememnto());
                values.put(KEY_BAIRRO, endereco.get(i).getBairro());
                values.put(KEY_CODIGOMUNICIPIO, endereco.get(i).getCodigoMunicipio());

                mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                        .getWritableDatabase().insertWithOnConflict(TABELA_BANCO,
                        null, values, SQLiteDatabase.CONFLICT_REPLACE);

            }

        }catch (Exception e){
            Log.i("INFO", "Erro ao salvar o contato" + e.getMessage());
            return false;
        }
        return true;
    }

    protected Endereco bind(Cursor c){
        Endereco endereco = new Endereco();
        endereco.setCodigoEndereco(c.getInt(c.getColumnIndex(KEY_CODIGO)));
        endereco.setCodigoCliente(c.getString(c.getColumnIndex(KEY_CODIGOCLIENTE)));
        endereco.setEndereco(c.getString(c.getColumnIndex(KEY_ENDERECO)));
        endereco.setNumero(c.getString(c.getColumnIndex(KEY_NUMERO)));
        endereco.setComplememnto(c.getString(c.getColumnIndex(KEY_COMPLEMENTO)));
        endereco.setBairro(c.getString(c.getColumnIndex(KEY_BAIRRO)));
        endereco.setCodigoMunicipio(c.getString(c.getColumnIndex(KEY_CODIGOMUNICIPIO)));
        return endereco;
    }

}

package com.example.app2.helper;

import android.content.Context;
import android.database.Cursor;

import com.example.app2.app.App2app;
import com.example.app2.database.DbHelper;
import com.example.app2.models.Municipio;

import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {

    private DbHelper mDbHelper;
    private final String TABELA_BANCO = "GUA_MUNICIPIOS";
    private static final String KEY_CODIGOMUNICIPIO = "MUN_CODIGOMUNICIPIO";
    private static final String KEY_NOME = "MUN_NOME";
    private static final String KEY_ESTADO = "MUN_ESTADO";

    private static MunicipioDAO sInstance;

    public static synchronized MunicipioDAO getInstance(){
        if(sInstance == null){
            sInstance = new MunicipioDAO();
        }
        return sInstance;
    }

    public MunicipioDAO(){
    }

    public Municipio buscarMunicipio(String codigo){

        String sql = "SELECT "+KEY_CODIGOMUNICIPIO+", "+KEY_NOME+", "+KEY_ESTADO+" " +
                "FROM "+TABELA_BANCO+" WHERE "+KEY_CODIGOMUNICIPIO+" = \""+codigo+"\"";

        Cursor c = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().rawQuery(sql, null);

        Municipio municipio = new Municipio();
        while (c.moveToNext()) {
            municipio.setCodigo_municipio(c.getString(c.getColumnIndex(KEY_CODIGOMUNICIPIO)));
            municipio.setCidade(c.getString(c.getColumnIndex(KEY_NOME)));
            municipio.setEstado(c.getString(c.getColumnIndex(KEY_ESTADO)));
        }

        return municipio;

    }

    public String buscarMunicipioNome(String nome){

        String sql = "SELECT "+KEY_CODIGOMUNICIPIO+" " +
                "FROM "+TABELA_BANCO+" WHERE "+KEY_NOME+" = \""+nome+"\"";

        Cursor c = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().rawQuery(sql, null);

        String municipio = null;
        while (c.moveToNext()) {
            municipio = c.getString(c.getColumnIndex(KEY_CODIGOMUNICIPIO));
        }

        return municipio;

    }

    public List<String> buscarMunicipioEstado(String estado){

        List<String> municipios = new ArrayList<>();

        String sql = "SELECT "+KEY_NOME+" " +
                "FROM "+TABELA_BANCO+" WHERE "+KEY_ESTADO+" = \""+estado+"\"";

        Cursor c = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            municipios.add(c.getString(c.getColumnIndex(KEY_NOME)));
        }

        return municipios;

    }

}

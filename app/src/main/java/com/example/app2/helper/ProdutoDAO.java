package com.example.app2.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.constraintlayout.solver.PriorityGoalRow;

import com.example.app2.app.App2app;
import com.example.app2.database.DbHelper;
import com.example.app2.models.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private DbHelper mDbHelper;
    private SQLiteDatabase mDqLiteDatabase;
    private final String TABELA_BANCO = "GUA_PRECOS";
    private static final String KEY_CODIGO = "PRO_CODIGO";
    private static final String KEY_PRECOS = "PRP_PRECOS";
    private static final String KEY_DESCRICAO = "PRO_DESCRICAO";
    private static final String KEY_STATUS = "PRO_STATUS";
    private static final String KEY_ESTOQUE = "ESE_ESTOQUE";
    private static final String KEY_PRECO_MAX= "PRECO_MAX";
    private static final String KEY_PRECO_MIN= "PRECO_MIN";

    public static ProdutoDAO sInstance;

    public static synchronized ProdutoDAO getInstance(){
        if(sInstance == null){
            sInstance = new ProdutoDAO();
        }
        return sInstance;
    }

    public ProdutoDAO() {
    }

    public List<Produto> obterTodos(){
        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT p.PRO_CODIGO, p.PRO_DESCRICAO, p.PRO_STATUS, e.ESE_ESTOQUE," +
                " MIN(pc.PRP_PRECOS) AS PRECO_MIN, MAX(pc.PRP_PRECOS) AS PRECO_MAX" +
                " FROM GUA_PRODUTOS p" +
                " INNER JOIN GUA_ESTOQUEEMPRESA e" +
                " ON p.PRO_CODIGO = e.ESE_CODIGO" +
                " INNER JOIN GUA_PRECOS pc" +
                " ON p.PRO_CODIGO = pc.PRP_CODIGO" +
                " GROUP BY p.PRO_CODIGO";

        Cursor c = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()){
            produtos.add(bind(c));
        }
        c.close();
        return produtos;
    }

    public List<String> buscarPrecos(String codigo){

        List<String> precos = new ArrayList<>();

        String sql = "SELECT PRP_PRECOS FROM "+TABELA_BANCO+" " +
                " WHERE PRP_CODIGO LIKE ? " +
                " GROUP BY PRP_PRECOS " +
                " ORDER BY PRP_PRECOS " +
                " ASC ";

        Cursor cursor = mDbHelper.getInstance(DBSelector.getInstance().getDbName())
                .getReadableDatabase()
                .rawQuery(sql, new String[]{"%".concat(codigo).concat("%")});

        while (cursor.moveToNext()){
            precos.add(cursor.getString(cursor.getColumnIndex("PRP_PRECOS")));
        }

        return precos;
    }

    protected Produto bind(Cursor c){
        Produto produto = new Produto();
        produto.setCodigo(c.getString(c.getColumnIndex(KEY_CODIGO)));
        produto.setDescricao(c.getString(c.getColumnIndex(KEY_DESCRICAO)));
        produto.setStatus(c.getString(c.getColumnIndex(KEY_STATUS)));
        produto.setEstoque(c.getString(c.getColumnIndex(KEY_ESTOQUE)));
        produto.setPrecoMax(c.getString(c.getColumnIndex(KEY_PRECO_MAX)));
        produto.setPrecoMin(c.getString(c.getColumnIndex(KEY_PRECO_MIN)));
        return produto;
    }

}

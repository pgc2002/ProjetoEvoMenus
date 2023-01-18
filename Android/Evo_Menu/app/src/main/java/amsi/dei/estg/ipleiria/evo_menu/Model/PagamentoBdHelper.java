package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import amsi.dei.estg.ipleiria.evo_menu.Model.Pagamento;

import java.util.ArrayList;

public class PagamentoBdHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "user";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String ID_PEDIDO = "idPedido";
    private final static String VALOR = "valor";
    private final static String METODO = "metodo";



    private SQLiteDatabase db;

    public PagamentoBdHelper(Context contexto)
    {
        super(contexto, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqltable = "CREATE TABLE " + TABLE_NAME
                + "( "
                + ID + " INTEGER PRIMARY KEY, "
                + ID_PEDIDO + " INTEGER NOT NULL, "
                + VALOR + " TEXT NOT NULL, "
                + METODO + " TEXT NOT NULL);";
        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Pagamento adicionarPagamentoBD(Pagamento pagamento)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, pagamento.getId());
        valores.put(ID_PEDIDO, pagamento.getIdPedido());
        valores.put(VALOR, pagamento.getValor());
        valores.put(METODO, pagamento.getMetodo());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            pagamento.setId(id);
            return pagamento;
        }
        return null;

    }
    public boolean editarPagamentoBD(Pagamento pagamento)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, pagamento.getId());
        valores.put(ID_PEDIDO, pagamento.getIdPedido());
        valores.put(VALOR, pagamento.getValor());
        valores.put(METODO, pagamento.getMetodo());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + pagamento.getId()});

        return nreg >0;
    }

    public boolean removerPagamentoBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<Pagamento> getAllPagamentosBD()
    {
        ArrayList<Pagamento> listaPagamentos = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, ID_PEDIDO, VALOR, METODO}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Pagamento aux = new Pagamento(cursor.getInt(0),
                        cursor.getInt(1),
                        cursor.getLong(2),
                        cursor.getString(3));
                listaPagamentos.add(aux);
            }while(cursor.moveToNext());
        }

        return listaPagamentos;
    }

    public void removerAllPagamentosBD()
    {
        db.delete(TABLE_NAME, null, null);
    }
}

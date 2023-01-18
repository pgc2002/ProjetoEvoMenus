package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PedidoBdHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "user";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String VALOR_TOTAL = "valorTotal";
    private final static String ESTADO = "estado";
    private final static String ID_CLIENTE = "idCliente";
    private final static String ID_RESTAURANTE = "idRestaurante";



    private SQLiteDatabase db;

    public PedidoBdHelper(Context contexto)
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
                + VALOR_TOTAL + " DOUBLE NOT NULL, "
                + ESTADO + " TEXT NOT NULL, "
                + ID_CLIENTE + " INTEGER NOT NULL, "
                + ID_RESTAURANTE + " INTEGER NOT NULL);";
        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Pedidos adicionarPedidoBD(Pedidos pedido)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, pedido.getId());
        valores.put(VALOR_TOTAL, pedido.getValor_total());
        valores.put(ESTADO, pedido.getEstado());
        valores.put(ID_CLIENTE, pedido.getId_cliente());
        valores.put(ID_RESTAURANTE, pedido.getId_restaurante());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            pedido.setId(id);
            return pedido;
        }
        return null;

    }
    public boolean editarPedidoBD(Pedidos pedido)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, pedido.getId());
        valores.put(VALOR_TOTAL, pedido.getValor_total());
        valores.put(ESTADO, pedido.getEstado());
        valores.put(ID_CLIENTE, pedido.getId_cliente());
        valores.put(ID_RESTAURANTE, pedido.getId_restaurante());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + pedido.getId()});

        return nreg >0;
    }

    public boolean removerPedidoBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<Pedidos> getAllPedidosBD()
    {
        ArrayList<Pedidos> listaPedidos = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, VALOR_TOTAL, ESTADO, ID_CLIENTE, ID_RESTAURANTE}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Pedidos aux = new Pedidos(cursor.getInt(0),
                        cursor.getDouble(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4));
                listaPedidos.add(aux);
            }while(cursor.moveToNext());
        }

        return listaPedidos;
    }

    public void removerAllPedidosBD()
    {
        db.delete(TABLE_NAME, null, null);
    }
}

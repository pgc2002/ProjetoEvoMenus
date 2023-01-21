package amsi.dei.estg.ipleiria.evo_menu.Model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class MesasDBHelper extends SQLiteOpenHelper
{
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "mesas";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String numero = "numero";
    private final static String capacidade = "capacidade";
    private final static String estado = "estado";
    private final static String id_restaurante = "id_restaurante";

    private SQLiteDatabase db;

    public MesasDBHelper(Context contexto)
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
                + numero + " INTEGER NOT NULL, "
                + capacidade + " INTEGER NOT NULL, "
                + estado + " TEXT NOT NULL, "
                + id_restaurante + " INTEGER);";

        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Mesa adicionarMesasDB(Mesa mesa)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, mesa.getId());
        valores.put(numero, mesa.getNumero());
        valores.put(capacidade, mesa.getCapacidade());
        valores.put(estado, mesa.getEstado());
        valores.put(id_restaurante, mesa.getId_restaurante());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            mesa.setId(id);
            return mesa;
        }
        return null;

    }
    public boolean editarMesaDB(Mesa mesa)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, mesa.getId());
        valores.put(numero, mesa.getNumero());
        valores.put(capacidade, mesa.getCapacidade());
        valores.put(estado, mesa.getEstado());
        valores.put(id_restaurante, mesa.getId_restaurante());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + mesa.getId()});

        return nreg >0;
    }

    public boolean removerMesaDB(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;

    }

    public ArrayList<Mesa> getAllMesasDB()
    {
        ArrayList<Mesa> listaMesas = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, numero, capacidade, estado, id_restaurante}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Mesa aux = new Mesa(cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2),
                    cursor.getString(3),
                    cursor.getInt(4));
                listaMesas.add(aux);
            }while(cursor.moveToNext());
        }

        return listaMesas;
    }

    public void removerAllMesasDB()
    {
        db.delete(TABLE_NAME, null, null);
    }


}

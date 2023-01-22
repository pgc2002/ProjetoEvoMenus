package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MoradaBdHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "morada";
    private final static int DB_VERSION = 2;
    private final static String ID = "id";
    private final static String PAIS = "pais";
    private final static String CIDADE = "cidade";
    private final static String RUA = "rua";
    private final static String CODPOST = "codpost";



    private SQLiteDatabase db;

    public MoradaBdHelper(Context contexto)
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
                + PAIS + " TEXT NOT NULL, "
                + CIDADE + " TEXT NOT NULL, "
                + RUA + " TEXT NOT NULL, "
                + CODPOST + " TEXT NOT NULL);";
        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Morada adicionarMoradaBD(Morada morada)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, morada.getId());
        valores.put(PAIS, morada.getPais());
        valores.put(CIDADE, morada.getCidade());
        valores.put(RUA, morada.getRua());
        valores.put(CODPOST, morada.getCodpost());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            morada.setId(id);
            return morada;
        }
        return null;

    }
    public boolean editarMoradaBD(Morada morada)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, morada.getId());
        valores.put(PAIS, morada.getPais());
        valores.put(CIDADE, morada.getCidade());
        valores.put(RUA, morada.getRua());
        valores.put(CODPOST, morada.getCodpost());


        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + morada.getId()});

        return nreg >0;
    }

    public boolean removerMoradaBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<Morada> getAllMoradasBD()
    {
        ArrayList<Morada> listaMoradas = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, PAIS, CIDADE, RUA, CODPOST}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Morada aux = new Morada(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)
                );
                listaMoradas.add(aux);
            }while(cursor.moveToNext());
        }

        return listaMoradas;
    }

    public void removerAllMoradasBD()
    {
        db.delete(TABLE_NAME, null, null);
    }
}

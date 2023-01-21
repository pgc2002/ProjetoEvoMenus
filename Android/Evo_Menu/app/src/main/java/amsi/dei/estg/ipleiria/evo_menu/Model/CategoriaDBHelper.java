package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CategoriaDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "categoria";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String NOME = "nome";
    private final static String IDRESTAURANTE = "idRestaurante";

    private SQLiteDatabase db;

    public CategoriaDBHelper(Context contexto)
    {
        super(contexto, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db)
    {
        String sqltable = "CREATE TABLE " + TABLE_NAME
                + "( "
                + ID + " INTEGER PRIMARY KEY, "
                + NOME + " TEXT NOT NULL, "
                + IDRESTAURANTE + " INTEGER NOT NULL);";
        db.execSQL(sqltable);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Categoria adicionarCategoriaDB(Categoria categoria)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, categoria.getId());
        valores.put(NOME, categoria.getNome());
        valores.put(IDRESTAURANTE, categoria.getIdRestaurante());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            categoria.setId(id);
            return categoria;
        }
        return null;

    }

    public boolean editarCategoriaDB(Categoria categoria)
    {
        ContentValues valores = new ContentValues();
        valores.put(NOME, categoria.getNome());
        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + categoria.getId()});

        return nreg >0;
    }

    public boolean removerCategoriaDB(int id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;

    }

    public ArrayList<Categoria> getAllCategoriasDB()
    {
        ArrayList<Categoria> listaCategorias = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, NOME, IDRESTAURANTE}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Categoria aux = new Categoria(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2)
                );
                listaCategorias.add(aux);
            }while(cursor.moveToNext());
        }

        return listaCategorias;
    }

    public void removerAllCategoriasDB()
    {
        db.delete(TABLE_NAME, null, null);
    }
}

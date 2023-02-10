package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class ItemDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "item";
    private final static int DB_VERSION = 3;
    private final static String ID = "id";
    private final static String NOME = "nome";
    private final static String FOTOGRAFIA = "fotografia";
    private final static String PRECO = "preco";
    private final static String IDCATEGORIA = "idCategoria";

    private SQLiteDatabase db;

    public ItemDBHelper(Context contexto)
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
                + FOTOGRAFIA + " TEXT NOT NULL, "
                + PRECO + " DOUBLE NOT NULL, "
                + IDCATEGORIA + " INTEGER NOT NULL);";
        db.execSQL(sqltable);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Item adicionarItemDB(Item item)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, item.getId());
        valores.put(NOME, item.getNome());
        valores.put(FOTOGRAFIA, item.getFotografia());
        valores.put(PRECO, item.getPreco());
        valores.put(IDCATEGORIA, item.getIdCategoria());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            item.setId(id);
            return item;
        }
        return null;
    }

    public boolean editarItemDB(Item item)
    {
        ContentValues valores = new ContentValues();
        valores.put(NOME, item.getNome());
        valores.put(FOTOGRAFIA, item.getFotografia());
        valores.put(PRECO, item.getPreco());
        valores.put(IDCATEGORIA, item.getIdCategoria());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + item.getId()});

        return nreg >0;
    }

    public boolean removerItemDB(int id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;

    }

    public ArrayList<Item> getAllItemsDB()
    {
        ArrayList<Item> listaCategorias = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, NOME, FOTOGRAFIA, PRECO, IDCATEGORIA}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Item aux = new Item(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getDouble(3),
                        cursor.getInt(4)
                );
                listaCategorias.add(aux);
            }while(cursor.moveToNext());
        }

        return listaCategorias;
    }

    public void removerAllItemsDB() {db.delete(TABLE_NAME, null, null);}
}

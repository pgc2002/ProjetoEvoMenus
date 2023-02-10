package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MenuDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "menu";
    private final static int DB_VERSION = 6;
    private final static String ID = "id";
    private final static String NOME = "nome";
    private final static String FOTOGRAFIA = "fotografia";
    private final static String DESCONTO = "desconto";
    private final static String IDCATEGORIA = "idCategoria";

    private SQLiteDatabase db;

    public MenuDBHelper(Context contexto)
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
                + DESCONTO + " DOUBLE NOT NULL, "
                + IDCATEGORIA + " INTEGER NOT NULL);";
        db.execSQL(sqltable);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Menu adicionarMenuDB(Menu menu)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, menu.getId());
        valores.put(NOME, menu.getNome());
        valores.put(FOTOGRAFIA, menu.getFotografia());
        valores.put(DESCONTO, menu.getDesconto());
        valores.put(IDCATEGORIA, menu.getIdCategoria());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            menu.setId(id);
            return menu;
        }
        return null;
    }

    public boolean editarMenuDB(Menu menu)
    {
        ContentValues valores = new ContentValues();
        valores.put(NOME, menu.getNome());
        valores.put(FOTOGRAFIA, menu.getFotografia());
        valores.put(DESCONTO, menu.getDesconto());
        valores.put(IDCATEGORIA, menu.getIdCategoria());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + menu.getId()});

        return nreg >0;
    }

    public boolean removerMenuDB(int id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<Menu> getAllMenusDB()
    {
        ArrayList<Menu> listaCategorias = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, NOME, FOTOGRAFIA, DESCONTO, IDCATEGORIA}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Menu aux = new Menu(
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

    public void removerAllMenusDB() {db.delete(TABLE_NAME, null, null);}
}

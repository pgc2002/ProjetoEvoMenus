package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.app.DownloadManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class RestauranteDBHelper extends SQLiteOpenHelper
{
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "restaurantes";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String NOME = "nome";
    private final static String EMAIL = "email";
    private final static String LOTACAOMAX = "lotacaoMaxima";
    private final static String TELEMOVEL = "telemovel";
    private final static String IDHORARIO = "idHorario";
    private final static String IDMORADA = "idMorada";

    private SQLiteDatabase db;

    public RestauranteDBHelper(Context contexto)
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
                + NOME + " TEXT NOT NULL, "
                + EMAIL + " TEXT NOT NULL, "
                + LOTACAOMAX + " INTEGER NOT NULL, "
                + TELEMOVEL + " TEXT NOT NULL, "
                + IDHORARIO + " INTEGER, "
                + IDMORADA + " INTEGER);";

        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Restaurante adicionarRestauranteBD(Restaurante restaurante)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, restaurante.getId());
        valores.put(NOME, restaurante.getNome());
        valores.put(EMAIL, restaurante.getEmail());
        valores.put(LOTACAOMAX, restaurante.getLotacao_max());
        valores.put(TELEMOVEL, restaurante.getTelemovel());
        valores.put(IDHORARIO, restaurante.getId_horario());
        valores.put(IDMORADA, restaurante.getId_morada());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            restaurante.setId(id);
            return restaurante;
        }
        return null;

    }
    public boolean editarRestauranteBD(Restaurante restaurante)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, restaurante.getId());
        valores.put(NOME, restaurante.getNome());
        valores.put(EMAIL, restaurante.getEmail());
        valores.put(LOTACAOMAX, restaurante.getLotacao_max());
        valores.put(TELEMOVEL, restaurante.getTelemovel());
        valores.put(IDHORARIO, restaurante.getId_horario());
        valores.put(IDMORADA, restaurante.getId_morada());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + restaurante.getId()});

        return nreg >0;
    }

    public boolean removerRestauranteBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;

    }

    public Restaurante getRestauranteBD(int idRestaurante){

        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_NAME + " WHERE id = '"+idRestaurante+"'", null);
        Restaurante aux = new Restaurante(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getInt(6));
        return aux;
    }

    public ArrayList<Restaurante> getAllRestaurantesBD()
    {
        ArrayList<Restaurante> listaRestaurantes = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, NOME, EMAIL, LOTACAOMAX, TELEMOVEL, IDHORARIO, IDMORADA}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Restaurante aux = new Restaurante(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getInt(6));
                listaRestaurantes.add(aux);
            }while(cursor.moveToNext());
        }

        return listaRestaurantes;
    }

    public void removerAllRestaurantesBD()
    {
        db.delete(TABLE_NAME, null, null);
    }
}


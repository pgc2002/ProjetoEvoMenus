package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import amsi.dei.estg.ipleiria.evo_menu.Model.Users;

public class UserBdHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "user";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String USERNAME = "username";
    private final static String AUTH_KEY = "auth_key";
    private final static String PASS_HASH = "pass_hash";
    private final static String EMAIL = "email";
    private final static String TELEMOVEL = "telemovel";
    private final static String NIF = "nif";
    private final static String NOME = "nome";
    private final static String ID_MORADA = "id_morada";
    private final static String DATA_CRIACAO = "data_criacao";
    private final static String DATA_UPDATE = "data_update";
    private final static String TIPO = "tipo";
    private final static String ID_MESA = "id_mesa";



    private SQLiteDatabase db;

    public UserBdHelper(Context contexto)
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
                + USERNAME + " TEXT NOT NULL, "
                + AUTH_KEY + " TEXT NOT NULL, "
                + PASS_HASH + " TEXT NOT NULL, "
                + TELEMOVEL + " TEXT NOT NULL, "
                + NIF + " TEXT NOT NULL, "
                + NOME + " TEXT NOT NULL, "
                + ID_MORADA + " INTEGER NOT NULL, "
                + DATA_CRIACAO + " INTEGER NOT NULL, "
                + DATA_UPDATE + " INTEGER NOT NULL, "
                + TIPO + " TEXT NOT NULL, "
                + ID_MESA + " INTEGER);";
        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public Users adicionarUserBD(Users user)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, user.getId());
        valores.put(USERNAME, user.getUsername());
        valores.put(AUTH_KEY, user.getAuth_key());
        valores.put(PASS_HASH, user.getPass_hash());
        valores.put(TELEMOVEL, user.getTelemovel());
        valores.put(NIF, user.getNif());
        valores.put(NOME, user.getNome());
        valores.put(ID_MORADA, user.getId_morada());
        valores.put(DATA_CRIACAO, user.getData_criacao());
        valores.put(DATA_UPDATE, user.getData_update());
        valores.put(TIPO, user.getTipo());

        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            user.setId(id);
            return user;
        }
        return null;

    }
    public boolean editarUserBD(Users user)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, user.getId());
        valores.put(USERNAME, user.getUsername());
        valores.put(AUTH_KEY, user.getAuth_key());
        valores.put(PASS_HASH, user.getPass_hash());
        valores.put(TELEMOVEL, user.getTelemovel());
        valores.put(NIF, user.getNif());
        valores.put(NOME, user.getNome());
        valores.put(ID_MORADA, user.getId_morada());
        valores.put(DATA_CRIACAO, user.getData_criacao());
        valores.put(DATA_UPDATE, user.getData_update());
        valores.put(TIPO, user.getTipo());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + user.getId()});

        return nreg >0;
    }

    public boolean removerUserBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<Users> getAllUsersBD()
    {
        ArrayList<Users> listaUsers = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, USERNAME, AUTH_KEY, PASS_HASH, EMAIL, TELEMOVEL, NIF, NOME, ID_MORADA, DATA_CRIACAO, DATA_UPDATE, TIPO}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Users aux = new Users(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getInt(8),
                        cursor.getLong(9),
                        cursor.getLong(10));
                listaUsers.add(aux);
            }while(cursor.moveToNext());
        }

        return listaUsers;
    }

    public void removerAllUsersBD()
    {
        db.delete(TABLE_NAME, null, null);
    }
}

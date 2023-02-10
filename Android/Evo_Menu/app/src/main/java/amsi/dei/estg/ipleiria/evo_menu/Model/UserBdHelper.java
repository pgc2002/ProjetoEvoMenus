package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UserBdHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "user";
    private final static int DB_VERSION = 3;
    private final static String ID = "id";
    private final static String USERNAME = "username";
    private final static String PASS = "pass";
    private final static String EMAIL = "email";
    private final static String TELEMOVEL = "telemovel";
    private final static String NIF = "nif";
    private final static String NOME = "nome";
    private final static String ID_MORADA = "id_morada";
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
                + NOME + " TEXT NOT NULL, "
                + PASS + " TEXT NOT NULL, "
                + EMAIL + " TEXT NOT NULL, "
                + TELEMOVEL + " TEXT NOT NULL, "
                + NIF + " TEXT NOT NULL, "
                + ID_MORADA + " INTEGER NOT NULL, "
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
    public User adicionarUserBD(User user)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, user.getId());
        valores.put(USERNAME, user.getUsername());
        valores.put(NOME, user.getNome());
        valores.put(PASS, user.getPass());
        valores.put(EMAIL, user.getEmail());
        valores.put(TELEMOVEL, user.getTelemovel());
        valores.put(NIF, user.getNif());
        valores.put(ID_MORADA, user.getId_morada());
        this.db.insert(TABLE_NAME, null, valores);
        /*int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            user.setId(id);
            return user;
        }*/
        return null;
    }
    public boolean editarUserBD(User user)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, user.getId());
        valores.put(USERNAME, user.getUsername());
        valores.put(PASS, user.getPass());
        valores.put(EMAIL, user.getEmail());
        valores.put(TELEMOVEL, user.getTelemovel());
        valores.put(NIF, user.getNif());
        valores.put(NOME, user.getNome());
        valores.put(ID_MORADA, user.getId_morada());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + user.getId()});

        return nreg >0;
    }

    public boolean removerUserBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;
    }

    public ArrayList<User> getAllUsersBD()
    {
        ArrayList<User> listaUsers = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, USERNAME, NOME, PASS, EMAIL, TELEMOVEL, NIF, ID_MORADA}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                User aux = new User(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getInt(7)
                );
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

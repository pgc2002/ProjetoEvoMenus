package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class HorarioDBHelper extends SQLiteOpenHelper
{
    private final static String DB_NAME = "evo_menus";
    private final static String TABLE_NAME = "horarios";
    private final static int DB_VERSION = 1;
    private final static String ID = "id";
    private final static String segunda_feira = "segunda_feira";
    private final static String terca_feira = "terca_feira";
    private final static String quarta_feira = "quarta_feira";
    private final static String quinta_feira = "quinta_feira";
    private final static String sexta_feira = "sexta_feira";
    private final static String sabado = "sabado";
    private final static String domingo = "domingo";

    private SQLiteDatabase db;


    public HorarioDBHelper(Context contexto)
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
                + segunda_feira + " TEXT NOT NULL, "
                + terca_feira + " TEXT NOT NULL, "
                + quarta_feira + " INTEGER NOT NULL, "
                + quinta_feira + " TEXT NOT NULL, "
                + sexta_feira + " TEXT NOT NULL, "
                + sabado + "TEXT NOT NULL, "
                + domingo + " TEXT NOT NULL);";

        db.execSQL(sqltable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    //Metodos crud
    public HorarioFuncionamento adicionarHorariosBD (HorarioFuncionamento horario)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, horario.getId());
        valores.put(segunda_feira, horario.getSegunda_feira());
        valores.put(terca_feira, horario.getTerca_feira());
        valores.put(quarta_feira, horario.getQuarta_feira());
        valores.put(quinta_feira, horario.getQuinta_feira());
        valores.put(sexta_feira, horario.getSexta_feira());
        valores.put(sabado, horario.getSabado());
        valores.put(domingo, horario.getDomingo());



        int id = (int) this.db.insert(TABLE_NAME, null, valores);

        if(id > -1)
        {
            horario.setId(id);
            return horario;
        }
        return null;

    }
    public boolean editarHorarioBD(HorarioFuncionamento Horario)
    {
        ContentValues valores = new ContentValues();
        valores.put(ID, Horario.getId());
        valores.put(segunda_feira, Horario.getSegunda_feira());
        valores.put(terca_feira, Horario.getTerca_feira());
        valores.put(quarta_feira, Horario.getQuarta_feira());
        valores.put(quinta_feira, Horario.getQuinta_feira());
        valores.put(sexta_feira, Horario.getSexta_feira());
        valores.put(sabado, Horario.getSabado());
        valores.put(domingo, Horario.getDomingo());

        int nreg = this.db.update(TABLE_NAME, valores, ID + " = ?" , new String []{"" + Horario.getId()});

        return nreg >0;
    }

    public boolean removerHorarioBD(long id)
    {
        int nreg = this.db.delete(TABLE_NAME, ID + " = ?", new String[]{"" + id});
        return nreg > 0;

    }

    public ArrayList<HorarioFuncionamento> getAllHorariosDB()
    {
        ArrayList<HorarioFuncionamento> listaHorarios = new ArrayList<>();
        Cursor cursor = this.db.query(TABLE_NAME, new String[]{ID, segunda_feira, terca_feira, quarta_feira, quinta_feira, sexta_feira, sabado, domingo}, null, null, null, null, null);
        if(cursor.moveToFirst())
        {
            do
            {
                HorarioFuncionamento aux = new HorarioFuncionamento(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7));
                listaHorarios.add(aux);
            }while(cursor.moveToNext());
        }

        return listaHorarios;
    }

    public void removerAllHorariosDB()
    {
        db.delete(TABLE_NAME, null, null);
    }
}

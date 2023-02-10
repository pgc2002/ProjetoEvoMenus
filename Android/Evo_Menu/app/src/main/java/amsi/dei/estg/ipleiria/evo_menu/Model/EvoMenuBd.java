package amsi.dei.estg.ipleiria.evo_menu.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EvoMenuBd extends SQLiteOpenHelper {
    private final static String DB_NAME = "evo_menus";
    private final static int DB_VERSION = 6;

    private static String TABLE_USER = "user";
    private static String TABLE_RESTAURANTE = "restaurante";
    private static String TABLE_MORADA = "morada";
    private static String TABLE_HORARIO = "horario";
    private static String TABLE_CATEGORIA = "categoria";
    private static String TABLE_ITEM = "item";
    private static String TABLE_MENU = "menu";
    private static String TABLE_PEDIDO = "pedido";
    private static String TABLE_PAGAMENTO = "pagamento";
    private static String TABLE_MESA = "mesa";

    private SQLiteDatabase db;

    public EvoMenuBd(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableUser = "CREATE TABLE " + TABLE_USER + "( id INTEGER PRIMARY KEY, username TEXT NOT NULL, nome TEXT NOT NULL, pass TEXT NOT NULL, email TEXT NOT NULL," +
                " telemovel TEXT NOT NULL, nif TEXT NOT NULL, id_morada INTEGER NOT NULL, id_mesa INTEGER);";
        db.execSQL(tableUser);

        String tableRestaurante = "CREATE TABLE " + TABLE_RESTAURANTE
                + "( id INTEGER PRIMARY KEY, nome TEXT NOT NULL, email TEXT NOT NULL, lotacaoMaxima INTEGER NOT NULL, telemovel TEXT NOT NULL, idHorario INTEGER, idMorada INTEGER);";
        db.execSQL(tableRestaurante);

        String tableMorada = "CREATE TABLE " + TABLE_MORADA + "( id INTEGER PRIMARY KEY, pais TEXT NOT NULL, cidade TEXT NOT NULL, rua TEXT NOT NULL, codpost TEXT NOT NULL);";
        db.execSQL(tableMorada);

        String tableHorario = "CREATE TABLE " + TABLE_HORARIO + "( id INTEGER PRIMARY KEY, segunda_feira TEXT NOT NULL, terca_feira TEXT NOT NULL, quarta_feira INTEGER NOT NULL, " +
                "quinta_feira TEXT NOT NULL, sexta_feira TEXT NOT NULL, sabado TEXT NOT NULL, domingo TEXT NOT NULL);";
        db.execSQL(tableHorario);

        String tableCategoria = "CREATE TABLE " + TABLE_CATEGORIA + "( id INTEGER PRIMARY KEY, nome TEXT NOT NULL, idRestaurante INTEGER NOT NULL);";
        db.execSQL(tableCategoria);

        String tableItem = "CREATE TABLE " + TABLE_ITEM + "( id INTEGER PRIMARY KEY, nome TEXT NOT NULL, fotografia TEXT NOT NULL, preco DOUBLE NOT NULL, idCategoria INTEGER NOT NULL);";
        db.execSQL(tableItem);

        String tableMenu = "CREATE TABLE " + TABLE_MENU + "( id INTEGER PRIMARY KEY, nome TEXT NOT NULL, fotografia TEXT NOT NULL, desconto DOUBLE NOT NULL, idCategoria INTEGER NOT NULL);";
        db.execSQL(tableMenu);

        String tablePedido = "CREATE TABLE " + TABLE_PEDIDO + "( id INTEGER PRIMARY KEY, valorTotal DOUBLE NOT NULL, estado TEXT NOT NULL, idCategoria INTEGER NOT NULL, idRestaurante INTEGER NOT NULL);";
        db.execSQL(tablePedido);

        String tablePagamento = "CREATE TABLE " + TABLE_PAGAMENTO + "( id INTEGER PRIMARY KEY, idPedido INTEGER NOT NULL, valor TEXT NOT NULL, metodo TEXT NOT NULL);";
        db.execSQL(tablePagamento);

        String tableMesa = "CREATE TABLE " + TABLE_MESA + "( id INTEGER PRIMARY KEY, numero INTEGER NOT NULL, capacidade INTEGER NOT NULL, estado TEXT NOT NULL, idRestaurante INTEGER);";
        db.execSQL(tableMesa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MORADA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HORARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAGAMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MESA);
        onCreate(db);
    }
}

package larissa.modulocompra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Larissa on 28/11/2016.
 */
public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProdutosDB";
    private static final String TABLE_NAME = "Produtos";
    private static final String KEY_PRODUTOID = "id";
    private static final String KEY_NOMEPRODUTO = "nome";
    private static final String KEY_MERCADO = "mercado";
    private static final String KEY_TIPOPRODUTO = "tipo";
    private static final String KEY_PRECOPRODUTO = "preco";
    private static final String[] COLUMNS = { KEY_PRODUTOID, KEY_MERCADO, KEY_TIPOPRODUTO,KEY_NOMEPRODUTO,KEY_PRECOPRODUTO };

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATION_TABLE = "CREATE TABLE Produtos ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "mercado TEXT, "+ "tipo TEXT, " + "nome TEXT, "
                + "preco DOUBLE )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }


    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "id IS NOT NULL";
        db.delete(TABLE_NAME, where, null);
        db.close();
    }

    public void deleteOne(Prod produto) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?",
                new String[]{String.valueOf(produto.getId())});
        db.close();

    }

    public Prod getProd(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Prod produto = new Prod();

        produto.setId(Integer.parseInt(cursor.getString(0)));
        produto.setMercadoProduto(cursor.getString(1));
        produto.setTipoProduto(cursor.getString(2));
        produto.setNomeProduto(cursor.getString(3));
        produto.setPrecoProduto(Double.parseDouble(cursor.getString(4)));

        return produto;
    }

    public List<Prod> allProdutos() {

        List<Prod> produtos = new LinkedList<Prod>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Prod produto = null;

        if (cursor.moveToFirst()) {
            do {
                produto = new Prod();
                produto.setId(Integer.parseInt(cursor.getString(0)));
                produto.setMercadoProduto(cursor.getString(1));
                produto.setTipoProduto(cursor.getString(2));
                produto.setNomeProduto(cursor.getString(3));
                produto.setPrecoProduto(Double.parseDouble(cursor.getString(4)));
                produtos.add(produto);
            } while (cursor.moveToNext());
        }

        return produtos;
    }


    public List<Prod> produtosFromMercado(String nomeMercado) {

        List<Prod> produtoslista = new LinkedList<Prod>();
        String[] args = new String[]{nomeMercado};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query("Produtos", null, "mercado = ?", args, null, null, null);
        Prod produto = null;

        if (cursor.moveToFirst()) {
            do {
                produto = new Prod();
                produto.setId(Integer.parseInt(cursor.getString(0)));
                produto.setMercadoProduto(cursor.getString(1));
                produto.setTipoProduto(cursor.getString(2));
                produto.setNomeProduto(cursor.getString(3));
                produto.setPrecoProduto(Double.parseDouble(cursor.getString(4)));
                produtoslista.add(produto);
            } while (cursor.moveToNext());
        }

        return produtoslista;
    }

    public void addProduto(Prod produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_MERCADO, produto.getMercadoProduto());
        values.put(KEY_TIPOPRODUTO, produto.getTipoProduto());
        values.put(KEY_NOMEPRODUTO, produto.getNomeProduto());
        values.put(KEY_PRECOPRODUTO, produto.getPrecoProduto());
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    public int updatePlayer(Prod produto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_MERCADO, produto.getMercadoProduto());
        values.put(KEY_TIPOPRODUTO, produto.getTipoProduto());
        values.put(KEY_NOMEPRODUTO, produto.getNomeProduto());
        values.put(KEY_PRECOPRODUTO, produto.getPrecoProduto());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[] { String.valueOf(produto.getId()) });

        db.close();

        return i;
    }


}

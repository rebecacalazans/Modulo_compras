package larissa.modulocompra;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class lista extends Activity {

    String qtdProd = null;
    String posProd = null;
    String nomeMercado = null;

    static final int PICK_PRODUT_REQUEST = 1;  // The request code

    private ListView lvProd;

    private List<Prod> produtos;
    private ListaProdAdapter produtoAdapter;
    private SQLiteDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (getIntent().hasExtra("nomeMercado")) {
                nomeMercado = extras.getString("nomeMercado");
            }

            if (getIntent().hasExtra("posProduto")) {
                posProd = extras.getString("posProduto");
            }

            if (getIntent().hasExtra("qtdProduto")) {
                qtdProd = extras.getString("qtdProduto");
            }
        }
        TextView merc = (TextView) findViewById(R.id.mercado);
        merc.setText(nomeMercado);

        lvProd = (ListView) findViewById(R.id.lista);
        db = new SQLiteDatabaseHandler(this);

        boolean verify = doesDatabaseExist(this, "db");

        if(verify == false) {
            db = new SQLiteDatabaseHandler(this);

            db.addProduto(new Prod(1, "Mundial", "Limpeza", "Detergente", 4.90));
            db.addProduto(new Prod(2, "Mundial", "Limpeza", "Quardanapo", 5.10));
            db.addProduto(new Prod(3, "Mundial", "Carnes", "Frango Desossado", 14.90));
            db.addProduto(new Prod(4, "Mundial", "Biscoitos", "Treloso", 2.90));
            db.addProduto(new Prod(5, "Mundial", "Chocolate", "Barra Lacta", 7.00));
            db.addProduto(new Prod(6, "Mundial", "Chocolate", "5 star", 2.90));
            db.addProduto(new Prod(7, "Mundial", "Higiene", "Escova de dente", 9.90));
            db.addProduto(new Prod(8, "Mundial", "Higiene", "Pasta de dente", 8.90));
            db.addProduto(new Prod(9, "Mundial", "Higiene", "Sabonete", 1.90));
            db.addProduto(new Prod(10, "Zona Sul", "Alimentícios", "Feijao Preto", 10.70));
            db.addProduto(new Prod(11, "Zona Sul", "Alimentícios", "Arroz Integral", 6.45));
            db.addProduto(new Prod(12, "Zona Sul", "Alimentícios", "Arroz Branco", 3.45));
            db.addProduto(new Prod(13, "Zona Sul", "Limpeza", "Água Sanitária", 5.90));
            db.addProduto(new Prod(14, "Zona Sul", "Limpeza", "Sabão OMO", 15.10));
            db.addProduto(new Prod(15, "Zona Sul", "Carnes", "Frango peito", 16.90));
            db.addProduto(new Prod(16, "Zona Sul", "Biscoitos", "Choco Licia", 3.90));
            db.addProduto(new Prod(17, "Zona Sul", "Chocolate", "Barra Nestle", 6.50));
            db.addProduto(new Prod(18, "Zona Sul", "Chocolate", "Kit Kat", 2.95));
            db.addProduto(new Prod(19, "Zona Sul", "Higiene", "Escova de dente", 6.90));
            db.addProduto(new Prod(20, "Zona Sul", "Higiene", "Pasta de dente", 9.90));
            db.addProduto(new Prod(21, "Zona Sul", "Higiene", "Sabonete", 2.90));
            db.addProduto(new Prod(22, "Prezunic", "Limpeza", "Água Sanitária", 5.40));
            db.addProduto(new Prod(23, "Prezunic", "Limpeza", "Sabão OMO", 16.20));
            db.addProduto(new Prod(24, "Prezunic", "Alimentícios", "Açucar", 3.75));
            db.addProduto(new Prod(25, "Prezunic", "Alimentícios", "Feijao Preto", 9.86));
            db.addProduto(new Prod(26, "Prezunic", "Alimentícios", "Arroz Integral", 5.34));
            db.addProduto(new Prod(27, "Prezunic", "Alimentícios", "Arroz Branco", 4.87));
            db.addProduto(new Prod(28, "Prezunic", "Alimentícios", "Farinha", 4.45));
            db.addProduto(new Prod(29, "Prezunic", "Alimentícios", "Cuzcuz", 6.53));
            db.addProduto(new Prod(30, "Prezunic", "Carnes", "Frango Sobrecoxa", 12.90));
            db.addProduto(new Prod(31, "Prezunic", "Biscoitos", "Maizena", 1.99));
            db.addProduto(new Prod(32, "Prezunic", "Chocolate", "Barra Garoto", 6.90));
            db.addProduto(new Prod(33, "Prezunic", "Chocolate", "Bis", 4.95));
            db.addProduto(new Prod(34, "Prezunic", "Higiene", "Escova de dente", 7.55));
            db.addProduto(new Prod(35, "Prezunic", "Higiene", "Pasta de dente", 8.90));
            db.addProduto(new Prod(36, "Prezunic", "Higiene", "Sabonete", 2.65));
        }

        produtos = db.produtosFromMercado(nomeMercado);

        produtoAdapter = new ListaProdAdapter(this, produtos);
        lvProd.setAdapter(produtoAdapter);

        lvProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prod pAux = produtoAdapter.getItem(position);

                Intent myIntent = new Intent(getApplicationContext(), qtdProduto.class);

                Bundle b = new Bundle();
                b.putCharSequence("nomeProduto", pAux.getNomeProduto());
                b.putCharSequence("precoProduto", String.valueOf(pAux.getPrecoProduto()));
                b.putCharSequence("tipoProduto", pAux.getTipoProduto());
                b.putCharSequence("posicaoProduto", String.valueOf(position));
                b.putCharSequence("quantidadeProduto", String.valueOf(pAux.getQuantidadeProduto()));
                myIntent.putExtras(b);

                startActivityForResult(myIntent, PICK_PRODUT_REQUEST);
            }//fecha onItemClick
        });//fecha setOnItemClickListener
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_PRODUT_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Test for the code you have used to start the Activity

                posProd = data.getStringExtra("posProduto");
                qtdProd =  data.getStringExtra("qtdProduto");

                if (posProd != null) {
                    produtos.get(Integer.parseInt(posProd)).setQuantidadeProduto(Integer.parseInt(qtdProd));
                }

                lvProd.setAdapter(produtoAdapter);

                int qtdaux = 0;
                double precoTotalaux = 0;
                TextView qtdFinal = (TextView) findViewById(R.id.qtd);
                TextView precoFinal = (TextView) findViewById(R.id.valor);
                for (Prod p : produtos) {
                    qtdaux += p.getQuantidadeProduto();
                    precoTotalaux += p.getPrecoTotal();
                }
                qtdFinal.setText(String.valueOf(qtdaux));
                precoFinal.setText(String.format("%.2f",precoTotalaux));
            }
        }
    }

    public void finalizar(View v) {
        Intent intent = new Intent (this, pagamento.class);
        Bundle b = new Bundle();
        TextView quantidade = (TextView) findViewById(R.id.qtd);
        TextView valor = (TextView) findViewById(R.id.valor);
        String valorFinal = valor.getText().toString();
        String qtd = quantidade.getText().toString();
        b.putCharSequence("valorcompra", valorFinal);
        b.putCharSequence("quantidade", qtd);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }

    public static boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}



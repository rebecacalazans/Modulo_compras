package larissa.modulocompra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class qtdProduto extends Activity {

    String tipo;
    String nome;
    String preco;
    String qtd;
    String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtd_produto);

        Bundle extras = getIntent().getExtras();

        if (getIntent().getExtras() != null) {
            if (getIntent().hasExtra("nomeProduto")) {
                nome = extras.getString("nomeProduto");
            }
            if (getIntent().hasExtra("tipoProduto")) {
                tipo = extras.getString("tipoProduto");
            }
            if (getIntent().hasExtra("precoProduto")) {
                preco = extras.getString("precoProduto");
            }
            if (getIntent().hasExtra("posicaoProduto")) {
                pos = extras.getString("posicaoProduto");
            }

            if (getIntent().hasExtra("quantidadeProduto")) {
                qtd = extras.getString("quantidadeProduto");
            }
        }
        double precinho = Double.parseDouble(preco);
        String formatado = String.format("%.2f",precinho);

        TextView t = (TextView) findViewById(R.id.categoria);
        t.setText(tipo);
        TextView n = (TextView) findViewById(R.id.nomeproduto);
        n.setText(nome);
        TextView p = (TextView) findViewById(R.id.precoproduto);
        p.setText(formatado);
    }

    public void cancelar(View v) {
        Intent inte = new Intent(this, lista.class);
        Bundle b = new Bundle();
        b.putCharSequence("posProduto", pos);
        b.putCharSequence("qtdProduto", qtd);
        inte.putExtras(b);
        setResult(RESULT_OK, inte);
        finish();
    }

    public void adicionarProduto(View v) {
        EditText quantidade = (EditText) findViewById(R.id.editquantidade);
        String qtdcompra = quantidade.getText().toString();

        int qtdInicial = Integer.parseInt(qtd);
        int qtdNova = Integer.parseInt(qtdcompra);
        int result = qtdInicial + qtdNova;

        /*Toast.makeText(
                getBaseContext(),
                String.valueOf(qtdtotal),
                Toast.LENGTH_SHORT).show();*/

        Intent intent = new Intent();
        Bundle b = new Bundle();
        b.putCharSequence("posProduto", pos);
        b.putCharSequence("qtdProduto", String.valueOf(result));
        //b.putCharSequence("qtdProduto", qtd);
        intent.putExtras(b);
        setResult(RESULT_OK, intent);
        finish();
    }

}

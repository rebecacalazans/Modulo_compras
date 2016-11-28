package larissa.modulocompra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class pagamento extends Activity {

    String valor;
    String quantidade;
    private static final String TAG = "MEDIA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);
        Bundle extras = getIntent().getExtras();
        valor = extras.getString("valorcompra");
        quantidade = extras.getString("quantidade");

        TextView qtd = (TextView) findViewById(R.id.qtdfinal);
        TextView val = (TextView) findViewById(R.id.valorCompra);
        qtd.setText(quantidade);
        val.setText(valor);
    }

    public void Encerrar(View v) {

        ManageFile filewrite = new ManageFile(this);
        //Avisa o usuário se a gravação foi bem sucedida
        if(filewrite.WriteFile("**********NOTA FISCAL********** \n" +
                "Quantidade de produtos = " + quantidade +"\n" +
                "Valor Total = " + valor)
                == true){
            Toast.makeText(this,
                    "Nota Fiscal gravada com sucesso.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,
                    "Não foi possível escrever o texto.",
                    Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent (this, telainicial.class);
        startActivity(intent);
        finish();
    }
}

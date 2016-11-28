package larissa.modulocompra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class selectMercado extends Activity {

    private Spinner spn;
    private List<String> nomes = new ArrayList<String>();
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_mercado);

        //Adicionando Nomes no ArrayList
        nomes.add("Mundial");
        nomes.add("Zona Sul");
        nomes.add("Prezunic");

        spn = (Spinner) findViewById(R.id.spinner);
        //Cria um ArrayAdapter usando um padrão de layout da classe R do android, passando o ArrayList nomes
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spn.setAdapter(spinnerArrayAdapter);

        //Método do Spinner para capturar o item selecionado
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                nome = parent.getItemAtPosition(posicao).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void ok(View v) {

        Intent intent = new Intent (this, lista.class);
        Bundle b = new Bundle();
        b.putCharSequence("nomeMercado", nome);
        intent.putExtras(b);
        startActivity(intent);
        finish();
    }


}

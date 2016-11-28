/*Grupo: Marina Mota, Larissa Fischer, Pedro Siaudzionis, Rebeca Calazans, Driele Neves*/

package larissa.modulocompra;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class telainicial extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telainicial);
    }

    public void iniciarCompra(View v) {
        Intent intent = new Intent (this, selectMercado.class);
        startActivity(intent);
        finish();
    }
}
